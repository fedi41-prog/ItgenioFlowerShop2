package com.fedi4.flowershop2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "product_id") val product_id: Int,
    @ColumnInfo(name = "count") val count: Int
) {
    fun toBasketEntry(): BasketEntry = BasketEntry(
        id = id,
        product_id = product_id,
        count = count
    )
}