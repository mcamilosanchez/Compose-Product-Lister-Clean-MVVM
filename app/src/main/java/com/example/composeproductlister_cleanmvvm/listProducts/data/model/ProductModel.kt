package com.example.composeproductlister_cleanmvvm.listProducts.data.model

import com.google.gson.annotations.SerializedName

data class ProductModel(
    /*    @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String*/
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String
)