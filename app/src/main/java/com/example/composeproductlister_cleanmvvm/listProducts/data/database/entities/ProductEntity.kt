package com.example.composeproductlister_cleanmvvm.listProducts.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    /* With PrimaryKey equal to TRUE we ensure that every time a ProductEntity object is created we
    will only pass the values that are after the ID (title, description, price...) and this value
    will be set automatically and will never be repeated.*/
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id" ) val id: Int = 0,
    @ColumnInfo(name = "title" ) val title: String,
    @ColumnInfo(name = "description" ) val description: String,
    @ColumnInfo(name = "price" ) val price: Double?,
    //@ColumnInfo(name = "discountPercentage" ) val discountPercentage: Double,
    //@ColumnInfo(name = "rating" ) val rating: String,
    //@ColumnInfo(name = "stock" ) val stock: Int,
    @ColumnInfo(name = "brand" ) val brand: String?,
    //@ColumnInfo(name = "category" ) val category: String,
    //@ColumnInfo(name = "thumbnail" ) val thumbnail: String,
    @ColumnInfo(name = "images" ) val images: ArrayList<String> = arrayListOf()
)


