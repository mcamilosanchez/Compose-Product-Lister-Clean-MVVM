package com.example.composeproductlister_cleanmvvm.listProducts.data.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductsModelData
import com.example.composeproductlister_cleanmvvm.listProducts.entities.ProductsEntity

fun ProductsModelData.toEntity(): ProductsEntity {
    with(this) {
        return ProductsEntity(
            products = products?.map { it.toEntity() },
            total = total,
            skip = skip,
            limit = limit
        )
    }
}