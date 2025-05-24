package com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model

import android.util.Log
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.composeproductlister_cleanmvvm.listProducts.data.ProductRepository
import com.example.composeproductlister_cleanmvvm.listProducts.ui.data.ProductModelUI
import com.example.composeproductlister_cleanmvvm.listProducts.ui.data.ShoppingCartProductUIModel
import com.example.composeproductlister_cleanmvvm.listProducts.ui.mapper.toProductUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    // This map keeps the product ID and its quantity
    private val _productsMapStateShoppingCartId = mutableStateMapOf<Int, Int>()

    // Main cart list with products and their quantities
    private val _mainListShoppingCart = mutableStateListOf<ShoppingCartProductUIModel>()
    val mainListShoppingCart: List<ShoppingCartProductUIModel> get() = _mainListShoppingCart

    private var _totalPriceShoppingCart = mutableDoubleStateOf(0.0)
    val totalPriceShoppingCart: State<Double> = _totalPriceShoppingCart


    init {
        fetchCartProducts()
    }

    fun addProductToShoppingCart(product: ProductModelUI) {
        val productId = product.id
        // If the product is already in the cart, increase its quantity
        val existingProduct = _mainListShoppingCart.find { it.id == productId }
        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(
                quantity = existingProduct.quantity + 1,
                priceShoppingCart = (product.price ?: 0.0) * (existingProduct.quantity + 1)
            )
            _mainListShoppingCart[_mainListShoppingCart.indexOf(existingProduct)] = updatedProduct
            calculateTotalPriceShoppingCart()

        } else {
            _mainListShoppingCart.add(
                ShoppingCartProductUIModel(
                    id = product.id,
                    title = product.title,
                    thumbnail = product.thumbnail,
                    stock = product.stock,
                    quantity = 1,
                    priceShoppingCart = product.price ?: 0.0
                )
            )
            calculateTotalPriceShoppingCart()
        }
    }

    private fun fetchCartProducts() {
        viewModelScope.launch {
            try {
                _mainListShoppingCart.clear()
                _productsMapStateShoppingCartId.forEach { (idProduct, quantity) ->
                    /** .toProductUIModel() converts ProductModelDomain to ProductModelUI and can
                     * be null. If productModelUI is null, let is not executed. If productModelUI
                     * is not null, it enters let and is added to the map. **/
                    repository.getProductById(idProduct)?.toProductUIModel()?.let { productModelUI ->
                        _mainListShoppingCart.add(
                            ShoppingCartProductUIModel(
                                id = productModelUI.id,
                                title = productModelUI.title,
                                thumbnail = productModelUI.thumbnail,
                                stock = productModelUI.stock,
                                quantity = quantity,
                                priceShoppingCart = (productModelUI.price ?: 0.0) * quantity
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("ShoppingCartViewModel", "Error fetching cart products", e)
            }
        }
    }

    fun onProductRemove(productId: Int) {
        _mainListShoppingCart.removeAll { it.id == productId }
        calculateTotalPriceShoppingCart()
    }

    fun onProductReduceQty(productId: Int) {
        val existingProduct = _mainListShoppingCart.find { it.id == productId }
        if (existingProduct != null) {
            if (existingProduct.quantity > 1) {
                val updatedProduct = existingProduct.copy(
                    quantity = existingProduct.quantity -1,
                    priceShoppingCart = (existingProduct.priceShoppingCart / existingProduct.quantity) * (existingProduct.quantity - 1)
                )
                _mainListShoppingCart[_mainListShoppingCart.indexOf(existingProduct)] = updatedProduct
                calculateTotalPriceShoppingCart()
            } else {
                onProductRemove(productId)
                calculateTotalPriceShoppingCart()
            }
        }
    }

    fun onProductAddQty(productId: Int) {
        val existingProduct = _mainListShoppingCart.find { it.id == productId }
        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(
                quantity = existingProduct.quantity + 1,
                priceShoppingCart = (existingProduct.priceShoppingCart / existingProduct.quantity) * (existingProduct.quantity + 1)
            )
            _mainListShoppingCart[mainListShoppingCart.indexOf(existingProduct)] = updatedProduct
            calculateTotalPriceShoppingCart()
        }
    }

    private fun calculateTotalPriceShoppingCart() {
        _totalPriceShoppingCart.doubleValue = _mainListShoppingCart.sumOf { it.priceShoppingCart }
    }
}