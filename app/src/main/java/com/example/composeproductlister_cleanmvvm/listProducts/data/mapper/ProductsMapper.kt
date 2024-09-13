package com.example.composeproductlister_cleanmvvm.listProducts.data.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductsModelData
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductsModelDomain

fun ProductsModelData.toModel(): ProductsModelDomain {
    with(this) {
        return ProductsModelDomain(
            products = products?.map {
                it.toModel()
            },
            total = total,
            skip = skip,
            limit = limit
        )
    }
}