package com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model

import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
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

    // This map contains a key ProductModelDomain and his value Quantity (Int) :
    private val _productsMapStateShoppingCart = mutableStateMapOf<ProductModelDomain, Int>()
    val productsMapStateShoppingCart: Map<ProductModelDomain, Int> = _productsMapStateShoppingCart

    private var _productPriceShoppingCart = mutableDoubleStateOf(0.0)
    val productPriceShoppingCart: MutableDoubleState = _productPriceShoppingCart

    fun addProductToShoppingCart(product: ProductModelDomain) {
        _listProductsCart.add(product)
        _productsMapStateShoppingCart.clear()
        _productsMapStateShoppingCart.putAll(_listProductsCart.groupingBy { it }.eachCount())
    }

    fun getProductById(productId: Int) = liveData<ProductModelDomain?> {
        emit(repository.getProductById(productId))
    }

    fun onProductRemove(product: ProductModelDomain) {
        _productsMapStateShoppingCart.remove(product) // Remove the product (key) from the map _productsMapStateShoppingCart
        _listProductsCart.removeAll { it == product } // And remove the product from the mutableStateListOf
    }

    fun onProductReduceQty(product: ProductModelDomain) {
        val currentQuantity  = _productsMapStateShoppingCart[product] ?: return //Get quantity of the product
        if (currentQuantity  > 1) {
            _productsMapStateShoppingCart[product] = currentQuantity - 1
           _listProductsCart.remove(product)
        } else {
            onProductRemove(product)
        }
    }

    fun onProductAddQty(product: ProductModelDomain) {
        val currentQuantity  = _productsMapStateShoppingCart[product] ?: return //Get quantity of the product
        _productsMapStateShoppingCart[product] = currentQuantity + 1
        _listProductsCart.add(product)
    }

    fun getPriceProduct(product: ProductModelDomain, quantity: Int) {
        _productPriceShoppingCart.doubleValue = product.price?.times(quantity) ?: 0.0
    }
}