package com.example.currencyconverter.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyConverted
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.presentation.state.CurrencyConverterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// currently, source currency is set as VND by default and always is VND when user open app.
// this should be change by using user locale or user setting and save it to data store for others usage
// but because this test is time limited, I will add this function to the to-do list
@HiltViewModel
class CurrencyConvertViewModel @Inject constructor(private val currencyViewModel: CurrencyViewModel) :
    ViewModel() {
    private val _state = mutableStateOf(CurrencyConverterState())
    val state: State<CurrencyConverterState> = _state
    private var flag =
        0 // use this flag to consider which currency is changed, source or destination
    private var selectedDestinationCurrency: String = ""

    init {
        loadingData()
        convertCurrency()
    }

    fun updateFlag(flag: Int, currencyCode: String) {
        this.flag = flag
        selectedDestinationCurrency = currencyCode
    }

    fun onChangeCurrency(currency: CurrencyDetail) {
        // determine which function to call based on flag variable
        if (flag == 0) {
            onChangeSourceCurrency(currency.code ?: "")
            currencyViewModel.loadingData(_state.value.sourceCurrency)
            return
        }
        onChangeDestinationCurrency(currency)
        convertCurrency()
    }

    private fun onChangeSourceCurrency(code: String) {
        _state.value = _state.value.copy(
            sourceCurrency = code,
            isShowChangeBs = false
        )
    }

    private fun onChangeDestinationCurrency(replaceCurrency: CurrencyDetail) {
        val index =
            _state.value.currencyList.indexOfFirst { item -> item.code == selectedDestinationCurrency }
        val newList = _state.value.currencyList.toMutableList()
        if (index != -1) {
            newList[index] = replaceCurrency
            _state.value = _state.value.copy(
                currencyList = newList,
                isShowChangeBs = false
            )
        }
    }

    fun onAddCurrencies(codes: List<String>) {
        currencyViewModel.addFavoriteCurrencies(codes)
        val newList = _state.value.currencyList.toMutableList()
        codes.forEach { code ->
            val tmp = currencyViewModel.supportedCurrencies.value?.values?.firstOrNull { item ->
                item.code == code
            }
            tmp?.let {
                newList += it
            }
        }
        _state.value = _state.value.copy(
            currencyList = newList,
            isShowSelectedBs = false
        )
        convertCurrency()
    }

    fun toggleSelectedBottomSheet() {
        _state.value = _state.value.copy(
            isShowSelectedBs = true
        )
    }

    fun hideSelectedBs() {
        _state.value = _state.value.copy(
            isShowSelectedBs = false
        )
    }

    fun toggleChangeBs() {
        _state.value = _state.value.copy(
            isShowChangeBs = true
        )
    }

    fun hideChangeBs() {
        _state.value = _state.value.copy(
            isShowChangeBs = false
        )
    }

    fun convertCurrency() {
        val result = mutableListOf<CurrencyConverted>()
        val currencyAmount = _state.value.currencyAmountInput

        // looping through each currency and convert with the latest rate
        _state.value.currencyList.forEach { currency ->
            val foundedItem = _state.value.latestRateCurrencies?.values?.firstOrNull { latestRate ->
                latestRate.code == currency.code
            }
            // because convert currency endpoint is unavailable since free plan does not supported,
            // I just convert the currency by multiple the input amount with the latest rate
            val convertedValue = if (currencyAmount.isNotEmpty()) {
                (foundedItem?.value ?: 0.0) * currencyAmount.toDouble()
            } else {
                0.0
            }
            result += CurrencyConverted(
                code = foundedItem?.code ?: "",
                convertedValue = convertedValue
            )
        }
        _state.value = _state.value.copy(
            convertedValue = result
        )
    }

    // used to get source currency detail data because source currency only store currency code
    fun getSourceCurrency(): CurrencyDetail {
        var result = CurrencyDetail()
        viewModelScope.launch {
            currencyViewModel.supportedCurrencies.collect { supportedCurrencies ->
                result =
                    supportedCurrencies?.values?.firstOrNull { it.code == _state.value.sourceCurrency }
                        ?: CurrencyDetail()
            }
        }
        return result
    }

    fun getRespectivelyConvertedData(code: String): CurrencyConverted? {
        return _state.value.convertedValue.firstOrNull { item ->
            item.code == code
        }
    }

    fun setCurrencyAmount(value: String) {
        _state.value = _state.value.copy(
            currencyAmountInput = value
        )
    }

    private fun loadingData() {
        val data = currencyViewModel.favoriteCurrencies.value.map { favorite ->
            findRespectivelyCurrency(favorite)
        }

        _state.value = _state.value.copy(
            currencyList = data
        )

        viewModelScope.launch {
            currencyViewModel.latestRates.collect { latestRates ->
                _state.value = _state.value.copy(latestRateCurrencies = latestRates)
                // use this code to convert new data after change currency to convert because
                // after change currency, data in currency viewmodel is reloaded
                convertCurrency()
            }
        }
    }

    // find currency detail from currency to get currency detail information based on currency code
    private fun findRespectivelyCurrency(currency: Currency): CurrencyDetail {
        var result = CurrencyDetail()
        viewModelScope.launch {
            currencyViewModel.supportedCurrencies.collect { supportedCurrencies ->
                result = supportedCurrencies?.values?.firstOrNull { it.code == currency.code }
                    ?: CurrencyDetail()
            }
        }
        return result
    }
}