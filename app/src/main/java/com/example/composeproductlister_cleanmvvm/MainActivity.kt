package com.example.composeproductlister_cleanmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.composeproductlister_cleanmvvm.core.navigation.NavigationWrapper
import com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model.DetailViewModel
import com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model.ProductsViewModel
import com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model.ShoppingCartViewModel
import com.example.composeproductlister_cleanmvvm.ui.theme.ComposeProductLister_CleanMVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val productsViewModel: ProductsViewModel by viewModels()
    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()

    // The ShoppingCartViewModel is created only once because it is instantiated in MainActivity with `by viewModels()`.
    // This means that the `init` block inside the ViewModel is also executed only once, when the instance is created.
    // As long as MainActivity is alive, the ViewModel is maintained, so the cart data is not lost.
    // Only if MainActivity is destroyed and recreated (for example, due to a configuration change or closing the app),
    // the ViewModel will be recreated and the `init` will be executed again.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeProductLister_CleanMVVMTheme {
                Surface {
                    //ScaffoldMainScreen(productsViewModel = productsViewModel)
                    NavigationWrapper(
                        productsViewModel,
                        shoppingCartViewModel,
                        detailViewModel
                    )
                }
            }
        }
    }
}