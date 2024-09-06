package com.example.composeproductlister_cleanmvvm.listProducts.data.network

import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiClient {

    @GET("/.json")
    suspend fun getAllProducts(): Response<List<ProductModel>>
}