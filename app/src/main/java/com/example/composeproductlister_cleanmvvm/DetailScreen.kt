package com.example.composeproductlister_cleanmvvm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Detail SnackScreen",
            fontSize = 25.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "product_image",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" +
                    " tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam," +
                    " quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo" +
                    " consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse" +
                    " cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat" +
                    " non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            style = MaterialTheme.typography.bodySmall
        )

    }
}