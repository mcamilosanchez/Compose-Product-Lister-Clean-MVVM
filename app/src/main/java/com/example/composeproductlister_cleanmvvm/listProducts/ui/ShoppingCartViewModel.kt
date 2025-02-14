package com.example.composeproductlister_cleanmvvm.listProducts.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.composeproductlister_cleanmvvm.listProducts.data.ProductRepository
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _listProductsCart = mutableStateListOf<ProductModelDomain>()

    private val _mapProductsCart = mutableMapOf<ProductModelDomain, Int>()
    val mapProductsCart: MutableMap<ProductModelDomain, Int> = _mapProductsCart

    fun listProductIdCart(product: ProductModelDomain) {
        _listProductsCart.add(product)

        _mapProductsCart.clear()
        _mapProductsCart.putAll(_listProductsCart.groupingBy { it }.eachCount())
    }

    fun getProductById(productId: Int) = liveData<ProductModelDomain?> {
        emit(repository.getProductById(productId))
    }
}