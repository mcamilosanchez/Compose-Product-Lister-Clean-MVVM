package com.example.composeproductlister_cleanmvvm.listProducts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.composeproductlister_cleanmvvm.listProducts.data.database.entities.ConverterArrayList
import com.example.composeproductlister_cleanmvvm.listProducts.data.database.entities.ProductEntity


@Database(entities = [ProductEntity::class], version = 1)
@TypeConverters(ConverterArrayList::class)
abstract class ProductDatabase: RoomDatabase() {

    //For each DAO you have to create an abstract function
    abstract fun getProductDao(): ProductDao

}