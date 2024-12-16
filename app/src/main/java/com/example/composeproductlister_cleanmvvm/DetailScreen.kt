package com.example.composeproductlister_cleanmvvm

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.composeproductlister_cleanmvvm.listProducts.domain.data.ProductModelDomain
import com.example.composeproductlister_cleanmvvm.listProducts.ui.DetailViewModel

@Composable
fun DetailScreen(
    productId: Int,
    detailViewModel: DetailViewModel,
    onBackClick: () -> Unit
) {

    //Query the product from the ViewModel
    val product = detailViewModel.getProductById(productId).observeAsState().value

    //Show content only if product is not null
    product?.let {
        DetailContent(product = it, onBackClick)
    } ?: run {
        Text(
            text = "Loading product...",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun DetailContent(product: ProductModelDomain, onBackClick: () -> Unit) {

    Scaffold(
        topBar = { TopAppBar(onBackClick) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            ImagesCarousel(product)

            Box(modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    RateProduct(product)
                    TitlePriceProduct(product)
                    Divider()
                    Description(product)
                    Divider()
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImagesCarousel(product: ProductModelDomain) {

    val pagerState = rememberPagerState(pageCount = { product.images.size })

    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) { page ->
            val imageUrl = product.images[page]
            GlideImage(
                model = imageUrl,
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp)),
                requestBuilderTransform = { requestOptions ->
                    requestOptions
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.placeholder_image)
                        .error(android.R.drawable.stat_notify_error)
                }
            )
        }
        if (product.images.size >=2) {
            Row(
                Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .align(Alignment.BottomCenter)
                    .offset(y = ((0).dp))
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(16.dp)
                    ),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary
                        else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(6.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RateProduct(product: ProductModelDomain) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp),
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Row(Modifier.align(Alignment.Center)) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "Starred",
                        tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = product.rating,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                    )
                }
            }
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = MaterialTheme.colorScheme.surfaceVariant)
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Row(Modifier.align(Alignment.Center)) {
                    Text(
                        text = "Stock: ",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                    )
                    Text(
                        text = product.stock.toString(),
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                    )
                }
            }
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                color = MaterialTheme.colorScheme.surfaceVariant)
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Row(Modifier.align(Alignment.Center).padding(start = 4.dp, end = 4.dp)) {
                    Text(
                        text = "Brand: ",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                    )
                    Text(
                        text = product.brand ?: "None",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun TitlePriceProduct(product: ProductModelDomain) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.title,
                fontSize = 25.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(0.6f, fill = false)
                    .padding(end = 12.dp)
            )
            Text(
                text = "$${product.price.toString()}",
                fontSize = 25.sp,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun Description(product: ProductModelDomain) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Description:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun Divider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .height(1.dp)
            .background(MaterialTheme.colorScheme.surfaceDim)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(onBackClick: () -> Unit) {
    androidx.compose.material3.TopAppBar(
        title = {
            Text(
                text = "Product Detail",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.secondary,
            navigationIconContentColor = MaterialTheme.colorScheme.secondary
        )
    )
}