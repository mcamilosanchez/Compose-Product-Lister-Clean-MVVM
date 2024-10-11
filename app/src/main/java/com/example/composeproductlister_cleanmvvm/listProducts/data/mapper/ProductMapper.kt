package com.example.composeproductlister_cleanmvvm.listProducts.data.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModelData
import com.example.composeproductlister_cleanmvvm.listProducts.entities.ProductEntity

fun ProductModelData.toEntity(): ProductEntity {
    with(this) {
        return ProductEntity(
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