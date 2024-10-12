package com.example.composeproductlister_cleanmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ProductsViewModel
import com.example.composeproductlister_cleanmvvm.ui.theme.ComposeProductLister_CleanMVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeProductLister_CleanMVVMTheme {
                Surface {
                    ScaffoldMainScreen(productsViewModel = productsViewModel)
                }
            }
        }
    }
}