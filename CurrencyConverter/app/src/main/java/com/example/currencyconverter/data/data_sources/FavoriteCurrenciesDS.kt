package com.example.currencyconverter.data.data_sources

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

// these functions are used to save favorite currencies into shared preferences data store
val Context.dataStore by preferencesDataStore(name = "favorite_currencies")

suspend fun Context.addFavoriteCurrency(newItem: String) {
    val preferences = dataStore.data
    val existingList = preferences.first()[FAVORITE_CURRENCIES_KEY]?.let {
        Gson().fromJson(it, Array<String>::class.java).toList()
    } ?: emptyList()
    if (existingList.contains(newItem)) {
        return
    }
    val newList = existingList + listOf(newItem)
    val gson = Gson()
    val jsonData = gson.toJson(newList)
    dataStore.edit { value ->
        value[FAVORITE_CURRENCIES_KEY] = jsonData
    }
}

suspend fun Context.addFavoriteCurrencies(newItems: List<String>) {
    val preferences = dataStore.data
    val existingList = preferences.first()[FAVORITE_CURRENCIES_KEY]?.let {
        Gson().fromJson(it, Array<String>::class.java).toList()
    } ?: emptyList()
    val newList = existingList.toMutableList()
    newItems.forEach { item ->
        if (!existingList.contains(item)) {
            newList += item
        }
    }
    val gson = Gson()
    val jsonData = gson.toJson(newList)
    dataStore.edit { value ->
        value[FAVORITE_CURRENCIES_KEY] = jsonData
    }
}

suspend fun Context.getFavoriteCurrencies(): List<String> {
    val preferences = dataStore.data.first()
    if (!preferences[FAVORITE_CURRENCIES_KEY].isNullOrEmpty()) {
        val jsonData = preferences[FAVORITE_CURRENCIES_KEY]
        return jsonData.let { json ->
            Gson().fromJson(json, Array<String>::class.java).toList()
        }
    }
    // if user launch app in the first time, there will be no favorite currency. Then, USD will be
    // add as default favorite currency
    addFavoriteCurrency("USD")
    return listOf("USD")
}

private val FAVORITE_CURRENCIES_KEY = stringPreferencesKey("favorite_currencies_key")
