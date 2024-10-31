package com.example.currencyconverter.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.common.LoadingDataState
import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.domain.repositories.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val currencyRepository: CurrencyRepository) :
    ViewModel() {
    private val _latestRates = MutableStateFlow<Map<String, Currency>?>(null)
    val latestRates: StateFlow<Map<String, Currency>?> = _latestRates.asStateFlow()

    private val _supportedCurrencies = MutableStateFlow<Map<String, CurrencyDetail>?>(null)
    val supportedCurrencies: StateFlow<Map<String, CurrencyDetail>?> =
        _supportedCurrencies.asStateFlow()


    private val _favoriteCurrencies = MutableStateFlow<List<Currency>>(emptyList())
    val favoriteCurrencies: StateFlow<List<Currency>> =
        _favoriteCurrencies.asStateFlow()

    private val _isGetDataSuccess = mutableStateOf<LoadingDataState>(LoadingDataState.Loading)
    val isGetDataSuccess: State<LoadingDataState> = _isGetDataSuccess

    init {
        loadingData()
    }

    fun loadingData(baseCurrency: String? = null) {
        _isGetDataSuccess.value = LoadingDataState.Loading
        viewModelScope.launch {
            if (baseCurrency != null) {
                _latestRates.value =
                    currencyRepository.getLatestRates(baseCurrency).data
            } else {
                _latestRates.value = currencyRepository.getLatestRates().data
            }
            _supportedCurrencies.value =
                currencyRepository.getSupportedCurrencies().data
            _isGetDataSuccess.value =
                if (_supportedCurrencies.value != null && _latestRates.value != null) LoadingDataState.Success else LoadingDataState.Failure
        }

        getFavoriteCurrencies()
    }

    fun addFavoriteCurrency(code: String) {
        viewModelScope.launch {
            currencyRepository.addFavoriteCurrency(code)
        }
    }

    // add favorite currencies to shared preferences data store
    fun addFavoriteCurrencies(codes: List<String>) {
        viewModelScope.launch {
            currencyRepository.addFavoriteCurrencies(codes)
        }
    }

    // get favorite currencies from shared preferences data store
    private fun getFavoriteCurrencies() {
        var favoriteCurrencies: List<Currency>
        viewModelScope.launch {
            _latestRates.collect { supportedCurrencies ->
                val favoriteCurrenciesStringList = currencyRepository.getFavoriteCurrenciesCode()
                favoriteCurrencies = favoriteCurrenciesStringList.mapNotNull { currencyCode ->
                    supportedCurrencies?.values?.find { it.code == currencyCode }
                }
                _favoriteCurrencies.value = favoriteCurrencies
            }
        }
    }

    // i use flag api to display flag image, and use this function to parse url with country code
    fun parseFlagUrl(currencyDetail: CurrencyDetail): String {
        if (!currencyDetail.countries.isNullOrEmpty()) {
            return "https://flagcdn.com/w160/${
                currencyDetail.countries[0].lowercase(Locale.ROOT)
            }.png"
        }
        return ""
    }
}