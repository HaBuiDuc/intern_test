package com.example.currencyconverter.common

import com.google.gson.annotations.SerializedName

// this data class is used to parse data from api since api always return data field and others redundant field
data class CurrencyApiResponse<T>(
    @SerializedName("data") val data: Map<String, T>?,
    val status: Boolean = false
)