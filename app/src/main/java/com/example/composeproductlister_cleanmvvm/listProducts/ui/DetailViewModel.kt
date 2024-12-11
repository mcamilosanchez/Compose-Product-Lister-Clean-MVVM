package com.example.composeproductlister_cleanmvvm.listProducts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.composeproductlister_cleanmvvm.listProducts.data.ProductRepository
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    fun getProductById(productId: Int) = liveData<ProductModelDomain?> {
        emit(repository.getProductById(productId))
    }
}