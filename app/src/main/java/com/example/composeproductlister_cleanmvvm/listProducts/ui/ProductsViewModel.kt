package com.example.composeproductlister_cleanmvvm.listProducts.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeproductlister_cleanmvvm.listProducts.domain.GetProductsCaseUse
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import com.example.composeproductlister_cleanmvvm.utils.ResultWrapper
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
    val products: List<ProductModelDomain> = _products

    private val _status = MutableLiveData<ResultWrapper<List<ProductModelDomain>>>()
    val status: LiveData<ResultWrapper<List<ProductModelDomain>>> = _status

    /////////////////////////////////////////NAVIGATION/////////////////////////////////////////////
    var onNavigateToDetail: ((Int) -> Unit)? = null

    fun navigateToDetailScreen(id: Int) {
        onNavigateToDetail?.invoke(id)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /* It's important to keep in mind the thread on which state variables are updated in Jetpack
    Compose, especially when working with components like AlertDialog that rely on UI recomposition.
    That's why we use POSTVALUE and not VALUE when we want to display Dialog*/

    fun onDialogClose() {
        _showDialog.postValue(false)
    }

    fun onDialogShow() {
        _showDialog.postValue(true)
    }

    /** In this method, we will call our use cases to return and store in memory all the
     * products. Since GetProductsCaseUse() returns a coroutine, we need the viewModelScope  **/
    fun onCreate() {
        viewModelScope.launch {
            _status.value = ResultWrapper.loading(data = null)
            try {
                val listProducts = getProductsUseCase()
                _status.value = ResultWrapper.success(listProducts)
                _products.clear()
                _products.addAll(listProducts ?: emptyList())
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = ResultWrapper.error(
                    data = null,
                    message = e.message ?: "Error calling ProductsUseCase"
                )
                onDialogShow()
                _products.clear()
            }
        }
    }
}