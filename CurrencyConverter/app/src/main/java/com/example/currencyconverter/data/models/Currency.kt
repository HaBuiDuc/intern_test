package com.example.currencyconverter.data.models

import com.google.gson.annotations.SerializedName

// currency value with latest rate
data class Currency(
    @SerializedName("code")
    val code: String,
    @SerializedName("value")
    val value: Double
)