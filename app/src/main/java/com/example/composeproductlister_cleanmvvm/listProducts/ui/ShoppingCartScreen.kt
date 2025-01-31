package com.example.composeproductlister_cleanmvvm.listProducts.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ShoppingCartScreen(detailViewModel: DetailViewModel) {

    val listProductsIdShoppingCart = detailViewModel.listProductIdCart

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Shopping Cart Screen: " + listProductsIdShoppingCart.size,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}