package com.example.composeproductlister_cleanmvvm.listProducts.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeproductlister_cleanmvvm.listProducts.data.mapper.toModel
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModelData
import com.example.composeproductlister_cleanmvvm.listProducts.domain.GetProductsCaseUse
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductsModelDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsCaseUse
) : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _products = mutableStateListOf<ProductModelDomain>()
    var products: List<ProductModelDomain> = _products

    val isLoading = MutableLiveData<Boolean>()

    /** In this method, we will call our use cases to return and store in memory all the
     * products. Since GetProductsCaseUse() returns a coroutine, we need the viewModelScope  **/
    fun onCreate() {
        viewModelScope.launch {

            val productModelDataResponse = getProductsUseCase()

            //We perform a mapper between the models of the Data layer to the Domain layer
            val productModelDomain = productModelDataResponse.toModel()
            val listProducts: List<ProductModelDomain>? = productModelDomain.products

            if (listProducts != null) {
                // Clear the list and add all the fetched products
                _products.clear()
                _products.addAll(listProducts)
            } else {
                _products.clear()
                _products.addAll(emptyList())
            }
        }
    }
}