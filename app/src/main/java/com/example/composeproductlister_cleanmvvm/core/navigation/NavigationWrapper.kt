package com.example.composeproductlister_cleanmvvm.core.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeproductlister_cleanmvvm.DetailScreen
import com.example.composeproductlister_cleanmvvm.ScaffoldMainScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ProductsScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.SearchProductScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model.ProductsViewModel
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ShoppingCartScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model.DetailViewModel
import com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model.ShoppingCartViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationWrapper(
    productsViewModel: ProductsViewModel,
    shoppingCartViewModel: ShoppingCartViewModel,
    detailViewModel: DetailViewModel
) {
    SharedTransitionLayout {
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
                        },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable

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
                            detailViewModel = detailViewModel,
                            productId = productId,
                            onBackClick = { navController.popBackStack() },
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedVisibilityScope = this@composable
                        )
                    }
                }
                composable<SearchProduct> {
                    SearchProductScreen()
                }
                composable<ShoppingCart> {
                    ShoppingCartScreen(
                        shoppingCartViewModel = shoppingCartViewModel
                    )
                }
            }
        }
    }
}