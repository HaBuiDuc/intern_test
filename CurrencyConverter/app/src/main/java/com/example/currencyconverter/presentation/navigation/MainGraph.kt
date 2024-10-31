package com.example.currencyconverter.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.currencyconverter.presentation.screens.CurrencyConverterScreen
import com.example.currencyconverter.presentation.screens.HomePage

@Composable
fun MainGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenList.HomeScreen.route
    ) {
        composable(
            route = ScreenList.HomeScreen.route
        ) {
            HomePage()
        }
        composable(
            route = ScreenList.CurrencyConverterScreen.route
        ) {
            CurrencyConverterScreen()
        }
    }
}