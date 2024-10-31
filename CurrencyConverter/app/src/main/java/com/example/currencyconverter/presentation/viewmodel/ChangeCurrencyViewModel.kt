package com.example.currencyconverter.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.presentation.state.ChangeCurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeCurrencyViewModel @Inject constructor(
    private val currencyViewModel: CurrencyViewModel,
    private val currencyConvertViewModel: CurrencyConvertViewModel
) :
    ViewModel() {
    private val _state = mutableStateOf(ChangeCurrencyState())
    val state: State<ChangeCurrencyState> = _state

    init {
        loadingData()
    }

    fun onChangeCurrency(currencyDetail: CurrencyDetail) {
        currencyConvertViewModel.onChangeCurrency(currencyDetail)
    }

    // loading available currencies to change
    private fun loadingData() {
        viewModelScope.launch {
            currencyViewModel.supportedCurrencies.collect { supportedCurrencies ->
                _state.value = _state.value.copy(
                    supportedCurrencies = supportedCurrencies?.values?.toList() ?: emptyList()
                )
            }
        }
    }

    fun parseFlag(currencyDetail: CurrencyDetail): String {
        return currencyViewModel.parseFlagUrl(currencyDetail)
    }
}