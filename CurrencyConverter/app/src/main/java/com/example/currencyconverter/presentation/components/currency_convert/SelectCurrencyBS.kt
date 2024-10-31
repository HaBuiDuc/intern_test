package com.example.currencyconverter.presentation.components.currency_convert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.presentation.theme.GreenB2C07A
import com.example.currencyconverter.presentation.viewmodel.SelectedCurrencyViewModel

// this bottom sheet is used to add favorite currency that user want to convert in converter screen
@Composable
fun SelectCurrencyBS(
    modifier: Modifier = Modifier,
    viewModel: SelectedCurrencyViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val state = viewModel.state
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height((screenHeight.value * 0.8).dp) // Set height to 80% of screen height
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f) // Allow LazyColumn to take available space
        ) {
            items(state.value.supportedCurrencies) { currency ->
                SelectedCurrencyItem(
                    currencyDetail = currency,
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        ElevatedButton(
            contentPadding = PaddingValues(0.dp),
            onClick = { viewModel.onAddCurrency() },
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                containerColor = GreenB2C07A
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.add_currency),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.Black
                )
            )
        }
    }
}