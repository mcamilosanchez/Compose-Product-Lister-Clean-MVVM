package com.example.composeproductlister_cleanmvvm.listProducts.entities

data class ProductsEntity(
    val products: List<ProductEntity>?,
    val total: Int,
    val skip: Int,
    val limit: Int
)
