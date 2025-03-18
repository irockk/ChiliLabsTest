package com.example.chililabstest.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chililabstest.features.catalog.presentation.CatalogRoute
import com.example.chililabstest.features.details.presentation.DetailsRoute
import com.example.chililabstest.ui.components.SnackbarHandler

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigator(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    SnackbarHandler { snackbarHostState, showSnackbar ->
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) {
            NavHost(
                modifier = modifier,
                navController = navController,
                startDestination = Screen.Catalog.route
            ) {
                composable(route = Screen.Catalog.route) {
                    CatalogRoute(
                        navController = navController
                    )
                }
                composable(route = Screen.Details.route) {
                    DetailsRoute(
                        navController = navController
                    )
                }
            }
        }
    }
}