package com.example.composeproductlister_cleanmvvm

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeproductlister_cleanmvvm.core.navigation.Home
import com.example.composeproductlister_cleanmvvm.core.navigation.ShoppingCart
import com.example.composeproductlister_cleanmvvm.listProducts.ui.DetailViewModel
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ProductsScreen
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ProductsViewModel
import com.example.composeproductlister_cleanmvvm.listProducts.ui.ShoppingCartScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldMainScreen(
    productsViewModel: ProductsViewModel,
    detailViewModel: DetailViewModel,
    navController: NavController,
    navigateToDetail: (Int) -> Unit
) {
    /////////////////////////////////////////NAVIGATION/////////////////////////////////////////////
    //val navController = rememberNavController()
    productsViewModel.onNavigateToDetail = navigateToDetail
    ////////////////////////////////////////////////////////////////////////////////////////////////

    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "General Store",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(onClick = { navController.navigate(Home) }) {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Home"
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Search products",
                            )
                        }
                        IconButton(onClick = {  navController.navigate(ShoppingCart)  }) {
                            Icon(
                                Icons.Filled.ShoppingCart,
                                contentDescription = "ShoppingCart",
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Filled.AccountCircle,
                                contentDescription = "User Account",
                            )
                        }
                    }
                },
            )
        },
/*        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }*/
    ) { innerPadding ->

        /*NavHost(
            navController = navController,
            startDestination = "HomeProducts",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("HomeProducts") {
                ProductsScreen(
                    productsViewModel = productsViewModel,
                    navigateToDetail  = navigateToDetail
                )
            }
            composable("ShoppingCart") {
                ShoppingCartScreen(
                    detailViewModel = detailViewModel
                )
            }
        }*/

        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ProductsScreen(
                productsViewModel = productsViewModel,
                navigateToDetail  = navigateToDetail
            )
        }
    }
}