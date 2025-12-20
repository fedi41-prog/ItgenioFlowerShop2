package com.fedi4.flowershop2

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fedi4.flowershop2.db.BasketDatabase
import com.fedi4.flowershop2.db.BasketEntity
import com.fedi4.flowershop2.db.BasketEntry

class BasketViewModel(application: Application) : ViewModel() {
    var dataa: LiveData<List<BasketEntity>>? = null
    var db: BasketDatabase? = Dependencies.getAppDatabase(application)

    //fun init(context: Context) {
    //    val appDatabase = Dependencies.getAppDatabase(context)
    //    val basketDao = appDatabase.getStatisticDao()
    //    repository = BasketRepository(basketDao)
    //}

    fun getData(): LiveData<List<BasketEntity>>? {
        if (dataa == null) {
            dataa = db?.getStatisticDao()?.getAllBasketEntries()

        }
        return dataa
    }
    fun insert(entity: BasketEntity) {
        db?.getStatisticDao()?.insertNewBasketEntry(entity)
    }
}