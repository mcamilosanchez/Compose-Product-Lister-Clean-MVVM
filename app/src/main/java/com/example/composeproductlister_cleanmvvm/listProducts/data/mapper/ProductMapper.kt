package com.example.composeproductlister_cleanmvvm.listProducts.data.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.data.database.entities.ProductEntity
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain

fun ProductModelDomain.toDatabase() = ProductEntity(
    title = title,
    description = description,
    price = price,
    brand = brand,
    images = images
)