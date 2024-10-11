package com.example.composeproductlister_cleanmvvm.listProducts.entities

data class ProductEntity(
    val id: Int = 0,
    val title: String,
    val description: String,
    val price: Double?,
    //val discountPercentage: Double,
    //val rating: String,
    //val stock: Int,
    val brand: String?,
    //val category: String,
    //val thumbnail: String,
    val images: ArrayList<String> = arrayListOf()
)
