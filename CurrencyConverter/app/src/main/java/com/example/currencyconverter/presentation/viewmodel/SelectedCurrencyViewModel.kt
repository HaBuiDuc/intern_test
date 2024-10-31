package com.example.currencyconverter.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.presentation.state.SelectedCurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedCurrencyViewModel @Inject constructor(
    private val currencyViewModel: CurrencyViewModel,
    private val currencyConvertViewModel: CurrencyConvertViewModel
) :
    ViewModel() {
    private val _state = mutableStateOf(SelectedCurrencyState())
    val state: State<SelectedCurrencyState> = _state

    init {
        loadingData()
    }

    fun onAddCurrency() {
        currencyConvertViewModel.onAddCurrencies(_state.value.selectedCurrencies)
        _state.value = _state.value.copy(
            selectedCurrencies = emptyList()
        )
    }

    fun isSelectedItem(code: String): Boolean {
        return _state.value.selectedCurrencies.contains(code)
    }

    fun onSelectedItem(code: String) {
        if (isSelectedItem(code)) {
            val newList = _state.value.selectedCurrencies - code
            _state.value = _state.value.copy(
                selectedCurrencies = newList
            )
            return
        }
        val newList = _state.value.selectedCurrencies + code
        _state.value = _state.value.copy(
            selectedCurrencies = newList
        )
    }

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