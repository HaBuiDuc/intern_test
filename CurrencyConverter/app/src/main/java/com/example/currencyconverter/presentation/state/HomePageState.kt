package com.example.currencyconverter.presentation.state

import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyDetail

data class HomePageState(
    val latestRateCurrencies: Map<String, Currency>? = null,
    val supportedCurrencies: Map<String, CurrencyDetail>? = null,
    val favoriteCurrencies: List<Currency> = emptyList()
)