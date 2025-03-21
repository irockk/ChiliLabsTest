package com.example.chililabstest.features.catalog.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun CatalogRoute(navController: NavController) {
    val viewModel = koinViewModel<CatalogViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    CatalogScreen(
        uiState = uiState,
        updateSearchText = viewModel::setSearchText
    )
}


