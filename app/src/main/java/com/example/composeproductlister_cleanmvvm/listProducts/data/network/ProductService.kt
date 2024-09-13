package com.example.composeproductlister_cleanmvvm.listProducts.data.network

import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductsModelData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/** The objective of this class is to query information from the Internet, which will be called
 * later by a repository. Here we are respecting one of the SOLID principles **/

class ProductService @Inject constructor(private val api: ProductApiClient) {

    /** This suspended function will return a list of products loaded through a call to a web
     * service, which is executed in a secondary thread so as not to saturate the UI. **/

    /*suspend fun getProducts(): List<ProductModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<List<ProductModel>> =
                api.getAllProducts()
            response.body() ?: emptyList()
        }
    }*/

    suspend fun getProduct(): ProductsModelData {
        return withContext(Dispatchers.IO) {

            val response: Response<ProductsModelData> = api.getProducts()

            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                throw Exception("Error fetching product: ${response.code()} - ${response.message()}")
            }
        }
    }
}