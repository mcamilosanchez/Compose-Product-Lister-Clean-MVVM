package com.example.composeproductlister_cleanmvvm.listProducts.data.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModelData
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain

fun ProductModelData.toModel(): ProductModelDomain {
    with(this) {
        return ProductModelDomain(
            id,
            title,
            description,
            //price,
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