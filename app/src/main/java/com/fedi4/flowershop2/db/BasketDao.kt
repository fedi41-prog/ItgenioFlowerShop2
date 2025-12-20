package com.fedi4.flowershop2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fedi4.flowershop2.db.BasketEntity

@Dao
interface BasketDao {
    @Insert(entity = BasketEntity::class)
    fun insertNewBasketEntry(entity: BasketEntity)

    @Query("SELECT id, product_id, count from basket")
    fun getAllBasketEntries(): LiveData<List<BasketEntity>>?

    @Query("DELETE FROM basket WHERE id = :entryId")
    fun deleteBasketEntryById(entryId: Long)
}