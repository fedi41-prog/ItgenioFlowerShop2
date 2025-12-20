package com.fedi4.flowershop2.db

data class BasketEntry(
    val id: Int,
    val product_id: Int,
    val count: Int
) {

    fun toBasketDbEntity(): BasketEntity = BasketEntity(
        id = 0,
        product_id = product_id,
        count = count
    )
}