package com.example.currencyconverter.data.data_sources

import com.example.currencyconverter.common.Constants.API_KEY
import com.example.currencyconverter.common.CurrencyApiResponse
import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyConvertApi {
    @GET("latest")
    suspend fun getLatestRates(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("base_currency") baseCurrency: String = "VND",
        @Query("type") type: String = "fiat",
    ): CurrencyApiResponse<Currency>


    @GET("currencies")
    suspend fun getSupportedCurrencies(@Query("apikey") apiKey: String = API_KEY): CurrencyApiResponse<CurrencyDetail>

    // can not use this end point because free plan is not supported
    @GET("convert")
    suspend fun convertCurrency(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("base_currency") baseCurrency: String = "VND",
        @Query("value") currencyAmount: String
    ): CurrencyApiResponse<Currency>
}