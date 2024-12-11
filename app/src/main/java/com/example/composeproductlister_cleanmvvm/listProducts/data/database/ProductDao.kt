package com.example.composeproductlister_cleanmvvm.listProducts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composeproductlister_cleanmvvm.listProducts.data.ProductRepository
import com.example.composeproductlister_cleanmvvm.listProducts.data.database.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table ORDER BY rating DESC ")
    suspend fun getAllProducts(): List<ProductEntity>

    //If there is a conflict when performing an insert, we can do a REPLACE, FAIL, ABORT...
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM product_table WHERE idProduct = :idProduct LIMIT 1")
    suspend fun getProductById(idProduct: Int): ProductEntity?

}