package com.example.composeproductlister_cleanmvvm.listProducts.ui.mapper

import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import com.example.composeproductlister_cleanmvvm.listProducts.ui.data.ShoppingCartProductUIModel

fun ProductModelDomain.toShoppingCartUIModel(): ShoppingCartProductUIModel {
    return with(this) {
        ShoppingCartProductUIModel(
            id = id,
            title = title,
            thumbnail = thumbnail,
            stock = stock,
            quantity = 0,
            priceShoppingCart = price ?: 0.0
        )
    }
}

