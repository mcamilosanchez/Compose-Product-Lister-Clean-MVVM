package com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model

import android.util.Log
import androidx.compose.runtime.MutableDoubleState
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

    // List containing all products
    private val _listProductsCart = mutableStateListOf<ProductModelUI>()

    // This map contains a key IdProduct (Int) and his value Quantity (Int)
    private val _productsMapStateShoppingCartId = mutableStateMapOf<Int, Int>()

    // This map contains a key Product (ProductModelUI) and his value Quantity (Int)
    private var _productsMapStateShoppingCart = mutableStateMapOf<ProductModelUI, Int>()

    private var _productPriceShoppingCart = mutableDoubleStateOf(0.0)
    val productPriceShoppingCart: MutableDoubleState = _productPriceShoppingCart

    private val _mainListShoppingCart = mutableStateListOf<ShoppingCartProductUIModel>()
    val mainListShoppingCart: List<ShoppingCartProductUIModel> get() = _mainListShoppingCart

    init {
        fetchCartProducts()
    }

    fun addProductToShoppingCart(product: ProductModelUI) {

/*        val productId = product.id
        *//** .getOrDefault(productId, 0) -> If the productId exists, it gets its current value
         * (quantity in the cart) and increases it by 1. And if the productId does not exist, it
         * returns 0 by default. **//*
        _productsMapStateShoppingCartId[productId] =
            _productsMapStateShoppingCartId.getOrDefault(productId, 0) + 1

        _listProductsCart.add(product)
        _productsMapStateShoppingCart.clear()
        _productsMapStateShoppingCart.putAll(_listProductsCart.groupingBy { it }.eachCount())*/

        val productId = product.id

        val existingProduct = _mainListShoppingCart.find { it.id == productId }

        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(
                quantity = existingProduct.quantity + 1,
                priceShoppingCart = (product.price ?: 0.0) * (existingProduct.quantity + 1)
            )
            _mainListShoppingCart[_mainListShoppingCart.indexOf(existingProduct)] = updatedProduct
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
        }


    }

    fun getProductById(productId: Int) = liveData {
        val productDomain = repository.getProductById(productId)
        emit(productDomain?.toProductUIModel())
    }

    private fun fetchCartProducts() {
        viewModelScope.launch {
            try {
                _productsMapStateShoppingCart.clear()
                _productsMapStateShoppingCartId.forEach { (idProduct, quantity) ->
                    val productModelDomain = repository.getProductById(idProduct)
                    /** productModelDomain?.toProductUIModel() converts ProductModelDomain to
                     * ProductModelUI and can be null. If productModelUI is null, let is not
                     * executed. If productModelUI is not null, it enters let and is added to the
                     * map. **/
                    productModelDomain?.toProductUIModel()?.let { productModelUI ->
                        _productsMapStateShoppingCart[productModelUI] = quantity
                    }
                }
            } catch (e: Exception) {
                Log.e("ShoppingCartViewModel", "Error fetching cart products", e)
            }
        }
    }

    fun onProductRemove(product: ProductModelUI) {
        _productsMapStateShoppingCart.remove(product) // Remove the product (key) from the map _productsMapStateShoppingCart
        _listProductsCart.removeAll { it == product } // And remove the product from the mutableStateListOf
    }

    fun onProductReduceQty(product: ProductModelUI) {
        val currentQuantity  = _productsMapStateShoppingCart[product] ?: return //Get quantity of the product
        if (currentQuantity  > 1) {
            _productsMapStateShoppingCart[product] = currentQuantity - 1
           _listProductsCart.remove(product)
        } else {
            onProductRemove(product)
        }
    }

    fun onProductAddQty(product: ProductModelUI) {
        val currentQuantity  = _productsMapStateShoppingCart[product] ?: return //Get quantity of the product
        _productsMapStateShoppingCart[product] = currentQuantity + 1
        _listProductsCart.add(product)
    }

    fun getPriceProduct(product: ProductModelUI, quantity: Int) {
        _productPriceShoppingCart.doubleValue = product.price?.times(quantity) ?: 0.0
    }

    fun getMainListShoppingCart() {
        _mainListShoppingCart.clear()
        _productsMapStateShoppingCart.forEach { (productModelUI, quantity) ->
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
}