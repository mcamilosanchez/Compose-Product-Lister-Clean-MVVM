package com.example.composeproductlister_cleanmvvm.listProducts.data.network

import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModelData
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductsModelData
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiClient {

    @GET("/.json")
    suspend fun getAllProducts(): Response<List<ProductModelData>>

    @GET("products")
    suspend fun getProducts(): Response<ProductsModelData>
}