package com.example.composeproductlister_cleanmvvm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.composeproductlister_cleanmvvm.core.navigation.Home
import com.example.composeproductlister_cleanmvvm.core.navigation.ShoppingCart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldMainScreen(
    navController: NavController,
    content: @Composable (Modifier) -> Unit // Get a composable function with Modifier
) {
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
                        IconButton(onClick = {
                            navController.navigate(Home) {
                                popUpTo<Home> { inclusive = true }
                            }
                        }) {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Home"
                            )
                        }
/*                        IconButton(onClick = {
                            navController.navigate(SearchProduct) {
                                // Keeps the home screen in the stack
                                popUpTo(Home) { inclusive = false }
                                // Avoid duplicating ShoppingCart if it is already at the top
                                launchSingleTop = true
                            }
                        }) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "Search products",
                            )
                        }*/
                        IconButton(onClick = {
                            navController.navigate(ShoppingCart) {
                                // Keeps the home screen in the stack
                                popUpTo(Home) { inclusive = false }
                                // Avoid duplicating ShoppingCart if it is already at the top
                                launchSingleTop = true
                            }
                        }) {
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
        /** It is important to know that the content of the screens is being returned by
         * NavigationWrapper.kt **/
        content(Modifier.padding(innerPadding))
    }
}