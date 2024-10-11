package com.example.composeproductlister_cleanmvvm.listProducts.domain

import com.example.composeproductlister_cleanmvvm.listProducts.data.ProductRepository
import com.example.composeproductlister_cleanmvvm.listProducts.data.mapper.toEntity
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductsModelDomain
import com.example.composeproductlister_cleanmvvm.listProducts.domain.mapper.toDomain
import javax.inject.Inject

class GetProductsCaseUse @Inject constructor(
    private val repository: ProductRepository
){

    //The special keyword invoke allows you to handle objects as functions.
    suspend operator fun invoke(): ProductsModelDomain {
        val productsModelData = repository.getAllProducts()
        return productsModelData.toEntity().toDomain()
    }
}