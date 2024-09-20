package com.example.composeproductlister_cleanmvvm.listProducts.ui

import android.content.res.Resources.Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModelData
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import com.example.composeproductlister_cleanmvvm.ui.theme.ComposeProductLister_CleanMVVMTheme

@Composable
fun ProductsScreen(modifier: Modifier = Modifier, productsViewModel: ProductsViewModel) {

    ComposeProductLister_CleanMVVMTheme {
        val showDialog: Boolean by productsViewModel.showDialog.observeAsState(false)

        productsViewModel.onCreate()

        Box(modifier = Modifier.fillMaxSize()) {
            ProductsList(productsViewModel)
        }
    }
}


@Composable
fun ProductsList(productsViewModel: ProductsViewModel) {

    //Pendiente por revisar: El ProductModel de UI vs el de DATA
    val getProducts = productsViewModel.products

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(getProducts) { product ->
                ItemProduct(product = product)
            }
        }
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemProduct(product: ProductModelDomain) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Column {
            GlideImage(
                model = product.images[0],
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background),
                contentScale = ContentScale.Inside,
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .align(Alignment.Start),
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row (modifier = Modifier.fillMaxWidth()){
                Text(
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 12.dp)
                        .weight(0.5f),
                    text = product.brand ?: "Unknown brand",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier
                        .padding(end = 16.dp, bottom = 12.dp)
                        .weight(0.5f),
                    text = "$${product.price.toString() ?: "???"}",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
            }
        }
    }
}
