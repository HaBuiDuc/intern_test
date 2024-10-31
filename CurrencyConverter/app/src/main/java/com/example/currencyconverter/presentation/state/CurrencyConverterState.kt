package com.example.currencyconverter.presentation.state

import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyConverted
import com.example.currencyconverter.data.models.CurrencyDetail


data class CurrencyConverterState(
    val sourceCurrency: String = "VND",
    val currencyAmountInput: String = "0.0",
    val currencyList: List<CurrencyDetail> = emptyList(),
    val convertedValue: List<CurrencyConverted> = mutableListOf(),
    val latestRateCurrencies: Map<String, Currency>? = null,
    val isShowSelectedBs: Boolean = false,
    val isShowChangeBs: Boolean = false
)
