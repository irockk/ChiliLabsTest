package com.example.chililabstest.features.details.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.chililabstest.ui.theme.ChiliLabsTestTheme


@Composable
fun DetailsScreen(
) {
    Text("Details Screen")
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ChiliLabsTestTheme {
        DetailsScreen()
    }
}