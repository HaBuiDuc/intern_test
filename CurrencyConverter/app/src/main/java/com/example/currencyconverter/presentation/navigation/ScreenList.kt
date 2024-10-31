package com.example.currencyconverter.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenList(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object HomeScreen :
        ScreenList(route = "home_page", title = "Home", icon = Icons.Default.Home)

    data object CurrencyConverterScreen : ScreenList(
        route = "currency_converter_screen",
        title = "Converter",
        icon = Icons.Default.SwapVert
    )

}