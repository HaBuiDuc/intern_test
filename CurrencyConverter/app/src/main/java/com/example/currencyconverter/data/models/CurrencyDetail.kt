package com.example.currencyconverter.data.models

import com.google.gson.annotations.SerializedName

// currency detail but without latest rate
data class CurrencyDetail(
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("name_plural")
    val namePlural: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("countries")
    val countries: List<String>? = null
)