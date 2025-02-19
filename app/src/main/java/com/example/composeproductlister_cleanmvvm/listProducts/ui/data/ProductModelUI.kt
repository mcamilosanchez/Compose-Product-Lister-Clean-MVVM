package com.example.composeproductlister_cleanmvvm.listProducts.ui.data

data class ProductModelUI(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double?,
    val discountPercentage: Double,
    val rating: String,
    val stock: Int,
    val brand: String?,
    val category: String,
    val thumbnail: String,
    val images: ArrayList<String> = arrayListOf()
)
