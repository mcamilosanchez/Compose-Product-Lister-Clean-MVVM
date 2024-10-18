package com.example.composeproductlister_cleanmvvm.listProducts.domain

import com.example.composeproductlister_cleanmvvm.listProducts.data.ProductRepository
import com.example.composeproductlister_cleanmvvm.listProducts.data.mapper.toDatabase
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import javax.inject.Inject

/** This use case will decide whether to get the information from the API or from DATABASE(Room) **/
class GetProductsCaseUse @Inject constructor(
    private val repository: ProductRepository
){
    /** This use case is called only the first time, that is, when we open the app. This means that
     * when the app is opened, it will query the product information from the server. **/
    //The special keyword invoke allows you to handle objects as functions.
    suspend operator fun invoke(): List<ProductModelDomain> {
        val products = repository.getAllProductsFromApi()
        return if (products.isNotEmpty()) {
            /* I have to clean the list of products stored in Database(Room) every time the app is
             * started, because if it is not done, I will indefinitely save lists and lists of
             * products in Database  */
            repository.clearProducts()
            /* After cleaning the Database, we insert the products obtained from the API and map
             * them to the Database data model, which has to return an entity.*/
            repository.insertProducts(products.map { it.toDatabase() })
            /* Thanks to the insert from the previous step, we can now query the products from the
             * DATABASE(Room) and return them. */
            repository.getAllProductsFromDatabase()
        } else {
            /* If the list of products queried from the API is empty, then we do this: */
            repository.getAllProductsFromDatabase()
        }
    }
}