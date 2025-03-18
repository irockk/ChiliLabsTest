package com.example.chililabstest.ui.navigation

sealed class Screen(val route: String) {
    data object Catalog : Screen("catalog_route")
    data object Details : Screen("details_route")
}