package com.fedi4.flowershop2

object Products{
    val products = ArrayList<Product>()

    fun getProductByIndex(idx: Int): Product? {
        return products.get(idx)
    }

    fun addProduct(product: Product) {
        products.add(product)
    }
    fun getLength(): Int {
        return products.size
    }
}

