package com.example.currencyconverter.common

sealed interface LoadingDataState {
    data object Loading : LoadingDataState
    data object Success : LoadingDataState
    data object Failure : LoadingDataState
}