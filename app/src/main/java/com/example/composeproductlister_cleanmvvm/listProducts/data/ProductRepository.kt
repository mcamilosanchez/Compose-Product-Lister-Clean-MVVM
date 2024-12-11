package com.example.composeproductlister_cleanmvvm.listProducts.data

import com.example.composeproductlister_cleanmvvm.listProducts.data.database.ProductDao
import com.example.composeproductlister_cleanmvvm.listProducts.data.database.entities.ProductEntity
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModelData
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductsModelData
import com.example.composeproductlister_cleanmvvm.listProducts.data.network.ProductService
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import com.example.composeproductlister_cleanmvvm.listProducts.domain.mapper.toDomainProduct
import javax.inject.Inject

/** This class will be responsible for managing the source of data for the application, either
 * from a local database (DB) or through a remote API call using Retrofit. **/

class ProductRepository @Inject constructor(
    private val api: ProductService,
    private val productDao: ProductDao
){

    /** Get products from API */
    suspend fun getAllProductsFromApi(): List<ProductModelDomain> {
        /** The "response" variable is a coroutine that calls the backend and returns the
         * product list **/
        val response: ProductsModelData = api.getProduct()
        val responseListProducts: List<ProductModelData>? = response.products
        if (responseListProducts != null) {
            return responseListProducts.map { it.toDomainProduct() }
        }
        return emptyList()
    }

    /** Get products from Room Database */
    suspend fun getAllProductsFromDatabase(): List<ProductModelDomain> {
        val response: List<ProductEntity> = productDao.getAllProducts()
        return response.map { it.toDomainProduct() }
    }

    /** Insert products from Room Database */
    suspend fun insertProducts(products: List<ProductEntity>) {
        productDao.insertAll(products)
    }

    /** Delete products from Room Database */
    suspend fun clearProducts() {
        productDao.deleteAllProducts()
    }

    /** Get Product by its Id **/
    suspend fun getProductById(productId: Int) : ProductModelDomain? {
        val productEntity = productDao.getProductById(productId)
        return productEntity?.toDomainProduct()
    }

}