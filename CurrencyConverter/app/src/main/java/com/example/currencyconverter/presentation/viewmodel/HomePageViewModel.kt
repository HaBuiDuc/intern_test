package com.example.currencyconverter.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.presentation.state.HomePageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val currencyViewModel: CurrencyViewModel
) :
    ViewModel() {
    private val _state = mutableStateOf(HomePageState())
    val state: State<HomePageState> = _state

    init {
        loadingData()
    }

    fun findRespectivelyCurrency(currency: Currency): CurrencyDetail {
        return state.value.supportedCurrencies?.values?.find { it.code == currency.code }
            ?: CurrencyDetail()
    }

    private fun loadingData() {
        viewModelScope.launch {
            currencyViewModel.latestRates.collect { latestRates ->
                _state.value = _state.value.copy(latestRateCurrencies = latestRates)
            }
        }

        viewModelScope.launch {
            currencyViewModel.supportedCurrencies.collect { supportedCurrencies ->
                _state.value = _state.value.copy(supportedCurrencies = supportedCurrencies)
            }
        }

        viewModelScope.launch {
            currencyViewModel.favoriteCurrencies.collect { favoriteCurrencies ->
                _state.value = _state.value.copy(favoriteCurrencies = favoriteCurrencies)
            }
        }
    }

    fun parseFlagUrl(currencyDetail: CurrencyDetail): String {
        return currencyViewModel.parseFlagUrl(currencyDetail)
    }
}