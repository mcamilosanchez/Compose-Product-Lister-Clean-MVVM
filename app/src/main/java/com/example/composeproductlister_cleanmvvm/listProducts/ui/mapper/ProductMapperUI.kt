package com.example.composeproductlister_cleanmvvm.listProducts.ui.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import com.example.composeproductlister_cleanmvvm.listProducts.ui.data.ProductModelUI

fun ProductModelDomain.toProductUIModel(): ProductModelUI {
    return ProductModelUI(
        id = id,
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
}