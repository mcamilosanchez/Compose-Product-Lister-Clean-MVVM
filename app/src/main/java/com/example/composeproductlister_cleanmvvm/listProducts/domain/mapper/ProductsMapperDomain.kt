package com.example.composeproductlister_cleanmvvm.listProducts.domain.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductsModelDomain
import com.example.composeproductlister_cleanmvvm.listProducts.entities.ProductsEntity

fun ProductsEntity.toDomain(): ProductsModelDomain {
    return ProductsModelDomain(
        products = products?.map { it.toDomainProduct() },
        total = total,
        skip = skip,
        limit = limit
    )
}