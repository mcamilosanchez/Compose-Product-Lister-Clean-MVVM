package com.example.composeproductlister_cleanmvvm.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object SearchProduct

@Serializable
object ShoppingCart

@Serializable
object Detail {
    const val ROUTE = "detail/{productId}"
    fun createRoute(productId: Int): String = "detail/$productId"
}