package com.fedi4.flowershop2

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.fedi4.flowershop2.db.BasketDatabase
import com.fedi4.flowershop2.db.BasketEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BasketViewModel(application: Application) : ViewModel() {
    var liveData: LiveData<List<BasketEntity>>? = null
    var db: BasketDatabase? = Dependencies.getAppDatabase(application)
    private val newBasketEntity = MutableLiveData<BasketEntity>()

    //fun init(context: Context) {
    //    val appDatabase = Dependencies.getAppDatabase(context)
    //    val basketDao = appDatabase.getStatisticDao()
    //    repository = BasketRepository(basketDao)
    //}

    fun getData(): LiveData<List<BasketEntity>>? {
        if (liveData == null) {
            liveData = db?.getStatisticDao()?.getAllBasketEntries()

        }
        return liveData
    }
    fun insert(entity: BasketEntity)  = runBlocking {
        launch(Dispatchers.IO) {
            db?.getStatisticDao()?.insertNewBasketEntry(entity)
        }
        val result = async(Dispatchers.Default) {

            db?.getStatisticDao()?.getAllBasketEntries()
        }

        liveData = result.await()
    }
}