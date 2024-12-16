package com.example.composeproductlister_cleanmvvm.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeproductlister_cleanmvvm.DetailScreen
import com.example.composeproductlister_cleanmvvm.ScaffoldMainScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.DetailViewModel
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ProductsViewModel

@Composable
fun NavigationWrapper(
    productsViewModel: ProductsViewModel,
    detailViewModel: DetailViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            ScaffoldMainScreen(
                productsViewModel = productsViewModel,
                navigateToDetail = { productId ->
                    navController.navigate(Detail.createRoute(productId))
                }
            )
        }
        composable(
            route = Detail.Route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                DetailScreen(
                    detailViewModel = detailViewModel,
                    productId = productId,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}