package com.example.composeproductlister_cleanmvvm.listProducts.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.composeproductlister_cleanmvvm.listProducts.ui.data.ProductModelUI
import com.example.composeproductlister_cleanmvvm.listProducts.ui.view_model.ShoppingCartViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.composeproductlister_cleanmvvm.listProducts.ui.data.ShoppingCartProductUIModel

@Composable
fun ShoppingCartScreen(shoppingCartViewModel: ShoppingCartViewModel) {

    val mainListShoppingCart: List<ShoppingCartProductUIModel> by remember {
        derivedStateOf { shoppingCartViewModel.mainListShoppingCart }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                text = "Order (${mainListShoppingCart.size} items)",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge,
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                content = {
                    items(mainListShoppingCart.toList()) { productCartItem ->
                    ItemShoppingCart(
                            shoppingCartViewModel = shoppingCartViewModel,
                            productCart = productCartItem
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun ItemShoppingCart(
    shoppingCartViewModel: ShoppingCartViewModel,
    productCart: ShoppingCartProductUIModel
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier.padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageProduct(productCart.thumbnail)
            Spacer(modifier = Modifier.width(8.dp))
            InfoAndQtyProduct(shoppingCartViewModel, productCart)
        }
    }
}

@Composable
fun InfoAndQtyProduct(
    shoppingCartViewModel: ShoppingCartViewModel,
    productCart: ShoppingCartProductUIModel
) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .size(24.dp)
                .clip(CircleShape)
        ) {
            IconButton(
                onClick = { shoppingCartViewModel.onProductRemove(productCart.id) },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = productCart.title,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PriceProduct(
                modifier = Modifier.weight(0.5f),
                shoppingCartViewModel = shoppingCartViewModel,
                productCart = productCart,
            )

            QtyProduct(
                shoppingCartViewModel = shoppingCartViewModel,
                productCart = productCart,
                modifier = Modifier.weight(0.5f)
            )
        }
    }
}

@Composable
fun QtyProduct(
    shoppingCartViewModel: ShoppingCartViewModel,
    productCart: ShoppingCartProductUIModel,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = "Qty",
            textAlign = TextAlign.Start,
            fontSize = 18.sp,
        )
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            IconButton(
                onClick = {
                    shoppingCartViewModel.onProductAddQty(productCart.id)
                },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            text = productCart.quantity.toString(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            IconButton(
                onClick = {
                    shoppingCartViewModel.onProductReduceQty(productCart.id)
                },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun PriceProduct(
    modifier: Modifier,
    shoppingCartViewModel: ShoppingCartViewModel,
    productCart : ShoppingCartProductUIModel,
) {
    Text(
        modifier = modifier
            .padding(end = 16.dp),
        text = "$${"%.2f".format(productCart.priceShoppingCart)}",
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageProduct(productImage: String) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp)
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        if (productImage.isNotEmpty()) {
            GlideImage(
                model = productImage,
                contentDescription = "Product Shopping Cart Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No image available",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
