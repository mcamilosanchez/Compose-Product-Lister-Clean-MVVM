package com.example.composeproductlister_cleanmvvm.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeproductlister_cleanmvvm.DetailScreen
import com.example.composeproductlister_cleanmvvm.ScaffoldMainScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ProductsViewModel

@Composable
fun NavigationWrapper(productsViewModel: ProductsViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            ScaffoldMainScreen(
                productsViewModel = productsViewModel,
                navigateToDetail = { navController.navigate(Detail) }
            )
        }

        composable<Detail> {
            DetailScreen()
        }
    }
}