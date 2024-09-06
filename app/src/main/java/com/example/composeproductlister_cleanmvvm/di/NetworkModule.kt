package com.example.composeproductlister_cleanmvvm.di

import com.example.composeproductlister_cleanmvvm.listProducts.data.network.ProductApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/** In this module, we will create functions that we cannot instantiate (Retrofit and Interfaces)
 * using the conventional DaggerHilt tags. These classes are called Providers. Remember that
 * classes that provide dependencies are called Modules. **/
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //I have to provide Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //I have to provide a ProductClient interface
    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit): ProductApiClient {
        return retrofit.create(ProductApiClient::class.java)
    }
}