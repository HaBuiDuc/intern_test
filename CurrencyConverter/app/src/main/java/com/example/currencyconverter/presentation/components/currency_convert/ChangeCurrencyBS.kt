package com.example.currencyconverter.presentation.components.currency_convert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.presentation.viewmodel.ChangeCurrencyViewModel

// this bottom sheet is used to change currency that user want to convert in converter screen
@Composable
fun ChangeCurrencyBS(
    modifier: Modifier = Modifier,
    viewModel: ChangeCurrencyViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val state = viewModel.state
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height((screenHeight.value * 0.8).dp)
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(state.value.supportedCurrencies) { currency ->
                ChangeCurrencyItem(currencyDetail = currency)
            }
        }
    }
}