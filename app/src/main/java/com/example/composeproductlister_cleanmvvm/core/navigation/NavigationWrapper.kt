package com.example.composeproductlister_cleanmvvm.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeproductlister_cleanmvvm.DetailScreen
import com.example.composeproductlister_cleanmvvm.ScaffoldMainScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ProductsScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model.ProductsViewModel
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ShoppingCartScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model.ShoppingCartViewModel

@Composable
fun NavigationWrapper(
    productsViewModel: ProductsViewModel,
    shoppingCartViewModel: ShoppingCartViewModel
) {

    val navController = rememberNavController()

    ScaffoldMainScreen(navController = navController) { modifier ->
        /** Here, we are sending dynamic screens to ScaffoldMainScreen.kt as parameter "content"  **/
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = modifier // The modifier received from 'content' is used.
        ) {
            composable<Home> {
                ProductsScreen(
                    productsViewModel = productsViewModel,
                    navigateToDetail = { productId ->
                        navController.navigate(Detail.createRoute(productId))
                    }
                )
            }
            composable(
                route = Detail.ROUTE,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId")
                if (productId != null) {
                    DetailScreen(
                        shoppingCartViewModel = shoppingCartViewModel,
                        productId = productId,
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
            composable<ShoppingCart> {
                ShoppingCartScreen(
                    shoppingCartViewModel = shoppingCartViewModel
                )
            }
        }
    }
}