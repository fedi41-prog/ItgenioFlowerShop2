package com.fedi4.flowershop2.db
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//
//class BasketRepository(private val basketDao: BasketDao) {
//
//    suspend fun insertNewBasketEntry(statisticDbEntity: BasketEntity) {
//        withContext(Dispatchers.IO) {
//            basketDao.insertNewBasketEntry(statisticDbEntity)
//        }
//    }
//
//    suspend fun getAllBasketEntries(): LiveData<List<BasketEntry>> {
//        return withContext(Dispatchers.IO) {
//            var entities = basketDao.getAllBasketEntries();
//            var entries: List<BasketEntry> = listOf()
//            for (entity in entities) {
//                var entry: BasketEntry = entity.toBasketEntry()
//                entries += entry
//            }
//
//            val liveData = MutableLiveData<List<BasketEntry>>()
//            liveData.value = entries
//            //return@withContext MutableLiveData<List<BasketEntry>>()
//            return@withContext liveData;
//        }
//
//    }
//
//    suspend fun removeBasketEntryById(id: Long) {
//        withContext(Dispatchers.IO) {
//            basketDao.deleteBasketEntryById(id)
//        }
//    }
//
//
//}

