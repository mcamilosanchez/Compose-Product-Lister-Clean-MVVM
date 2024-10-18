package com.example.composeproductlister_cleanmvvm.di

import android.content.Context
import androidx.room.Room
import com.example.composeproductlister_cleanmvvm.listProducts.data.database.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** In this module, we will create functions that we cannot instantiate (Retrofit and Interfaces)
 * using the conventional DaggerHilt tags. These classes are called Providers. Remember that
 * classes that provide dependencies are called Modules. **/
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val PRODUCT_DATABASE_NAME = "product_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ProductDatabase::class.java, PRODUCT_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providesProductDao(db: ProductDatabase) = db.getProductDao()
}