package com.example.composeproductlister_cleanmvvm.listProducts.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModel
import com.example.composeproductlister_cleanmvvm.listProducts.domain.GetProductsCaseUse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsCaseUse
) : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _products = mutableStateListOf<ProductModel>()
    var products: List<ProductModel> = _products

    val isLoading = MutableLiveData<Boolean>()

    /** In this method, we will call our use cases to return and store in memory all the
     * products. Since GetProductsCaseUse() returns a coroutine, we need the viewModelScope  **/
    fun onCreate() {
        viewModelScope.launch {

            val result = getProductsUseCase()

            // Clear the list and add all the fetched products
            _products.clear()
            _products.addAll(result)

        }
    }
}