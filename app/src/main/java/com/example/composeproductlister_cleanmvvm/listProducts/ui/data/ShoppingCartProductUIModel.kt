package com.example.composeproductlister_cleanmvvm.listProducts.ui.data

import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain

data class ShoppingCartProductUIModel(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val stock: Int,
    val quantity: Int,
    val priceShoppingCart: Double
)
