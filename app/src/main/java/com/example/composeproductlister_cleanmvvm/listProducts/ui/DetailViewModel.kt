package com.example.composeproductlister_cleanmvvm.listProducts.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
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

    private val _listProductIdCart = mutableStateListOf<Int>()
    val listProductIdCart: MutableList<Int> = _listProductIdCart

    fun listProductIdCart(idProduct: Int) {
        _listProductIdCart.add(idProduct)
        Log.i("Shopping Cart", "List of ids shopping cart: ${listProductIdCart.size}")

    }

    fun getProductById(productId: Int) = liveData<ProductModelDomain?> {
        emit(repository.getProductById(productId))
    }

}