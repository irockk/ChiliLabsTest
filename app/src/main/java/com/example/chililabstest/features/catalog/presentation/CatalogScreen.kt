package com.example.chililabstest.features.catalog.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.chililabstest.ui.theme.ChiliLabsTestTheme

@Composable
fun CatalogScreen(
) {
    Text("Catalog Screen")
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ChiliLabsTestTheme {
        CatalogScreen()
    }
}