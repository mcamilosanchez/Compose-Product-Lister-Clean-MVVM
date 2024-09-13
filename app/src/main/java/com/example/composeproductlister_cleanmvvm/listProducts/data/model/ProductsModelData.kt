package com.example.composeproductlister_cleanmvvm.listProducts.data.model

import com.google.gson.annotations.SerializedName

data class ProductsModelData(
    @SerializedName("products")
    val products : List<ProductModelData>?,

    @SerializedName("total")
    val total : Int,

    @SerializedName("skip")
    val skip : Int,

    @SerializedName("limit")
    val limit : Int
)
