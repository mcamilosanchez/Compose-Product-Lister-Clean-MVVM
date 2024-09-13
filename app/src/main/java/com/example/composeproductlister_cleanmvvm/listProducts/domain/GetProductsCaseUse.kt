package com.example.composeproductlister_cleanmvvm.listProducts.domain

import com.example.composeproductlister_cleanmvvm.listProducts.data.ProductRepository
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModelData
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductsModelData
import javax.inject.Inject

class GetProductsCaseUse @Inject constructor(
    private val repository: ProductRepository
){

    //The special keyword invoke allows you to handle objects as functions.
    suspend operator fun invoke(): ProductsModelData {
        return repository.getAllProducts()
    }
}