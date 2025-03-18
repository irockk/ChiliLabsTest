package com.example.chililabstest.features.details.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsRoute(navController: NavController) {
    val viewModel = koinViewModel<DetailsViewModel>()

    DetailsScreen()
}