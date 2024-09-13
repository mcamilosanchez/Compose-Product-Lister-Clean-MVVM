package com.example.composeproductlister_cleanmvvm.listProducts.domain.data

data class ProductsModelDomain(
    val products: List<ProductModelDomain>?,
    val total: Int,
    val skip: Int,
    val limit: Int
)
