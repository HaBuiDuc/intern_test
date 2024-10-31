package com.example.currencyconverter.common

sealed interface NetworkConnectionState {
    data object Available : NetworkConnectionState
    data object Unavailable : NetworkConnectionState
}