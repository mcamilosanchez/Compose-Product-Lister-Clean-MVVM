package com.example.composeproductlister_cleanmvvm.listProducts.data.model

import javax.inject.Inject
import javax.inject.Singleton

/** This class will be a DB that will store a list of products **/
@Singleton
class ProductProvider @Inject constructor() {
    var products: List<ProductModel> = emptyList()
}