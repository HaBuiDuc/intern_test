package com.example.currencyconverter.domain.repositories

import com.example.currencyconverter.common.CurrencyApiResponse
import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyDetail

interface CurrencyRepository {
    suspend fun getLatestRates(baseCurrency: String = "VND"): CurrencyApiResponse<Currency>
    suspend fun getSupportedCurrencies(): CurrencyApiResponse<CurrencyDetail>
    suspend fun convertCurrency(currencyAmount: String): CurrencyApiResponse<Currency>
    suspend fun getFavoriteCurrenciesCode(): List<String>
    suspend fun addFavoriteCurrency(code: String)
    suspend fun addFavoriteCurrencies(codes: List<String>)
}