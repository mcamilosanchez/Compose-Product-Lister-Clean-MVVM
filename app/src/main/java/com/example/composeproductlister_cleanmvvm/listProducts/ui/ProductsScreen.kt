package com.example.composeproductlister_cleanmvvm.listProducts.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeproductlister_cleanmvvm.listProducts.data.model.ProductModel

@Composable
fun ProductsScreen(modifier: Modifier = Modifier, productsViewModel: ProductsViewModel) {

    val showDialog: Boolean by productsViewModel.showDialog.observeAsState(false)

    productsViewModel.onCreate()

    Box(modifier = Modifier.fillMaxSize()) {
        ProductsList(productsViewModel)
    }

}

@Composable
fun ProductsList(productsViewModel: ProductsViewModel) {

    //Pendiente por revisar: El ProductModel de UI vs el de DATA
    val getProducts = productsViewModel.products

    LazyColumn {
        items(getProducts) { product ->
            ItemProduct(product = product)
        }

    }
}


@Composable
fun ItemProduct(product: ProductModel) {
    Card(
        border = BorderStroke(2.dp, Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            //.padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { }) {
        Column {
            /*Image(
                painter = painterResource(id = superHero.photo),
                contentDescription = "SuperHero Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )*/
            Text(
                text = product.author,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = product.quote,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
        }
    }
}
