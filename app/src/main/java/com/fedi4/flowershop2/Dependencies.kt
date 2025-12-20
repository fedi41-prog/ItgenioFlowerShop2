package com.fedi4.flowershop2

import android.content.Context
import androidx.room.Room
import com.fedi4.flowershop2.db.BasketDatabase

object Dependencies {

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    val appDatabase: BasketDatabase by lazy {
        Room.databaseBuilder(applicationContext, BasketDatabase::class.java, "database.db")
            .createFromAsset("database.db")
            .build()
    }

    //fun getAppDatabase(context: Context): BasketDatabase {
    //    return Room.databaseBuilder(context, BasketDatabase::class.java, "database.db")
    //        .createFromAsset("database.db")
    //        .build()
    //}

    fun getAppDatabase(context: Context): BasketDatabase {
        return Room.databaseBuilder(context, BasketDatabase::class.java, "database")
            .fallbackToDestructiveMigration(true)

            .build()
    }

}