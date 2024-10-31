package com.example.currencyconverter.presentation.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.presentation.components.home_page.CurrencyRateItem
import com.example.currencyconverter.presentation.components.home_page.FavoriteCurrencyRateItem
import com.example.currencyconverter.presentation.theme.Black0A0A0A
import com.example.currencyconverter.presentation.viewmodel.HomePageViewModel

@Preview
@Composable
private fun HomePagePrev() {
    HomePage()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomePageViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Scaffold(
        containerColor = Black0A0A0A,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.latest_rates),
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.White
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Black0A0A0A,
                ),
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.favorite_rates),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 18.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
            ) {
                state.value.favoriteCurrencies.map { favorite ->
                    FavoriteCurrencyRateItem(
                        currencyDetail = viewModel.findRespectivelyCurrency(
                            favorite
                        ), currencyRate = favorite
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            Text(
                text = stringResource(R.string.all_currencies),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(bottom = 18.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                state.value.latestRateCurrencies?.values?.toList()
                    ?.map { currency ->
                        CurrencyRateItem(
                            currency = currency,
                            currencyDetail = viewModel.findRespectivelyCurrency(currency = currency)
                        )
                    }
            }
        }
    }

}