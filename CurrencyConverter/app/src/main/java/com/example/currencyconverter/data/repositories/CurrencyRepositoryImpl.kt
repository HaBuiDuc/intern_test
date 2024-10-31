package com.example.currencyconverter.data.repositories

import android.content.Context
import android.util.Log
import com.example.currencyconverter.common.CurrencyApiResponse
import com.example.currencyconverter.data.data_sources.CurrencyConvertApi
import com.example.currencyconverter.data.data_sources.addFavoriteCurrencies
import com.example.currencyconverter.data.data_sources.addFavoriteCurrency
import com.example.currencyconverter.data.data_sources.getFavoriteCurrencies
import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.domain.repositories.CurrencyRepository
import javax.inject.Inject


class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyConvertApi,
    private val context: Context
) :
    CurrencyRepository {
    override suspend fun getLatestRates(baseCurrency: String): CurrencyApiResponse<Currency> {
        val res: CurrencyApiResponse<Currency>?
        try {
            res = api.getLatestRates(baseCurrency = baseCurrency).copy(
                status = true
            )
        } catch (e: Exception) {
            Log.e(TAG, "can not get latest rates data")
            return CurrencyApiResponse(
                status = false,
                data = null
            )
        }
        return res
    }

    override suspend fun getSupportedCurrencies(): CurrencyApiResponse<CurrencyDetail> {
        val res: CurrencyApiResponse<CurrencyDetail>?
        try {
            res = api.getSupportedCurrencies()
        } catch (e: Exception) {
            Log.e(TAG, "can not get supported currencies data")
            return CurrencyApiResponse(
                status = false,
                data = null
            )
        }
        return res
    }

    override suspend fun convertCurrency(currencyAmount: String): CurrencyApiResponse<Currency> {
        val res: CurrencyApiResponse<Currency>?
        try {
            res = api.convertCurrency(currencyAmount = currencyAmount)
        } catch (e: Exception) {
            throw e
        }
        return res
    }

    override suspend fun getFavoriteCurrenciesCode(): List<String> {
        return context.getFavoriteCurrencies()
    }

    override suspend fun addFavoriteCurrency(code: String) {
        return context.addFavoriteCurrency(newItem = code)
    }

    override suspend fun addFavoriteCurrencies(codes: List<String>) {
        return context.addFavoriteCurrencies(codes)
    }
}

const val TAG = "CurrencyRepository"