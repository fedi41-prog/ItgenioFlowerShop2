package com.fedi4.flowershop2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fedi4.flowershop2.db.BasketEntity

@Database(
    version = 2,
    entities = [
        BasketEntity::class
    ]
)

abstract class BasketDatabase : RoomDatabase() {
    abstract fun getStatisticDao(): BasketDao
}