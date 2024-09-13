package com.example.composeproductlister_cleanmvvm.listProducts.data

import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModelData
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductProvider
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductsModelData
import com.example.composeproductlister_cleanmvvm.listProducts.data.network.ProductService
import javax.inject.Inject

/** This class will be responsible for managing the source of data for the application, either
 * from a local database (DB) or through a remote API call using Retrofit. **/

class ProductRepository @Inject constructor(
    private val api: ProductService,
    private val productProvider: ProductProvider
){

    suspend fun getAllProducts(): ProductsModelData {

        /** The "response" variable is a coroutine that calls the backend and returns the
         * product list **/
        val getProducts = api.getProduct()

        /** When the repository is executed for the first time, it will call the service
         * (api.getProducts) and the list it returns (listProducts), will be stored in
         * ProductProvider, which will be our personal DB **/

        //productProvider.products = getProducts
        return getProducts
    }

}