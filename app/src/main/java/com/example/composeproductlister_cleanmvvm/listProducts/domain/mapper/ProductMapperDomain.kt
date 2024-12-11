package com.example.composeproductlister_cleanmvvm.listProducts.domain.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.data.database.entities.ProductEntity
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModelData
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain

fun ProductModelData.toDomainProduct(): ProductModelDomain {
    with(this) {
        return ProductModelDomain(
            id,
            title,
            description,
            price,
            discountPercentage,
            rating,
            stock,
            brand,
            category,
            thumbnail,
            images
        )
    }
}

fun ProductEntity.toDomainProduct(): ProductModelDomain {
    with(this) {
        return ProductModelDomain(
            idProduct,
            title,
            description,
            price,
            discountPercentage,
            rating,
            stock,
            brand,
            category,
            thumbnail,
            images
        )
    }
}