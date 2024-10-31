package com.example.currencyconverter.presentation.state

import com.example.currencyconverter.data.models.CurrencyDetail

data class SelectedCurrencyState(
    val selectedCurrencies: List<String> = emptyList(),
    val supportedCurrencies: List<CurrencyDetail> = emptyList()
)
