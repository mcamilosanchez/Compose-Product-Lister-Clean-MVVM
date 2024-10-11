package com.example.composeproductlister_cleanmvvm.listProducts.domain.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import com.example.composeproductlister_cleanmvvm.listProducts.entities.ProductEntity

fun ProductEntity.toDomainProduct(): ProductModelDomain {
    with(this) {
        return ProductModelDomain(
            id,
            title,
            description,
            price,
            //discountPercentage,
            //rating,
            //stock,
            brand,
            //category,
            //thumbnail,
            images
        )
    }
}