package com.example.currencyconverter.presentation.state

import com.example.currencyconverter.data.models.CurrencyDetail

data class ChangeCurrencyState(
    val supportedCurrencies: List<CurrencyDetail> = emptyList()
)
