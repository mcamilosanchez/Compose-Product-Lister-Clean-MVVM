package com.example.composeproductlister_cleanmvvm.listProducts.data.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.data.database.entities.ProductEntity
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain

fun ProductModelDomain.toDatabase() = ProductEntity(
    idProduct = id,
    title = title,
    description = description,
    price = price,
    discountPercentage = discountPercentage,
    rating = rating,
    stock = stock,
    brand = brand,
    category = category,
    thumbnail = thumbnail,
    images = images
)