package com.example.chililabstest.features.catalog.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun CatalogRoute(navController: NavController) {
    val viewModel = koinViewModel<CatalogViewModel>()

    CatalogScreen()
}


