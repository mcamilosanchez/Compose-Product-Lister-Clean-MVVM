package com.example.composeproductlister_cleanmvvm.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Detail {
    const val Route = "detail/{productId}"
    fun createRoute(productId: Int): String = "detail/$productId"
}