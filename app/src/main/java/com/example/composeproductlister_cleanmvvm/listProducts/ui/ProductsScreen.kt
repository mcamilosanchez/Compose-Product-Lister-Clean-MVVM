package com.example.composeproductlister_cleanmvvm.listProducts.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import com.example.composeproductlister_cleanmvvm.ui.theme.ComposeProductLister_CleanMVVMTheme
import com.example.composeproductlister_cleanmvvm.utils.ResultWrapper
import com.example.composeproductlister_cleanmvvm.utils.Status

@Composable
fun ProductsScreen(
    productsViewModel: ProductsViewModel,
    navigateToDetail: (Int) -> Unit
) {

    ComposeProductLister_CleanMVVMTheme {
        val showErrorDialog by productsViewModel.showDialog.observeAsState(false)
        val productsStatus by productsViewModel.status.observeAsState(
            ResultWrapper.loading(data = null)
        )

        /* LaunchedEffect: Remember that Composable functions can be executed multiple times during
        UI recomposition, so it is crucial to control when side-effect operations such as network
        calls or database updates are executed.*/
        LaunchedEffect(key1 = Unit) {
            // key1 = Unit ensures that it is executed only once
            productsViewModel.onCreate()
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when(productsStatus.status) {
                Status.LOADING -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                Status.ERROR -> { productsViewModel.onDialogShow() }
                Status.SUCCESS ->  ProductsList(
                    products = productsViewModel.products,
                    navigateToDetail = navigateToDetail
                )
            }
            if (showErrorDialog) {
                AlertDialog(
                    onDismissRequest = { productsViewModel.onDialogClose() },
                    title = { Text("Error") },
                    text = { Text(productsStatus.message ?: "Unknown Error") },
                    confirmButton = {
                        Button(onClick = {
                            productsViewModel.onDialogClose()
                            productsViewModel.onCreate()
                        }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}


@Composable
fun ProductsList(
    products: List<ProductModelDomain>,
    navigateToDetail: (Int) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            items(products) { product ->
                ItemProduct(
                    product = product,
                    navigateToDetail = navigateToDetail
                )
            }
        }
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemProduct(
    product: ProductModelDomain,
    navigateToDetail: (Int) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navigateToDetail(product.id) // We call the navigation directly
           },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Column {
            if (product.thumbnail.isNotEmpty()) { // Check if the images list is not empty
                GlideImage(
                    model = product.thumbnail,
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.background),
                    contentScale = ContentScale.Inside,
                )
            } else {
                // Handle the case where there's no image
                // You can display a placeholder image or a text message here
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Text(
                        text = "No image available",
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
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
                    text = "$${product.price.toString()}",
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
