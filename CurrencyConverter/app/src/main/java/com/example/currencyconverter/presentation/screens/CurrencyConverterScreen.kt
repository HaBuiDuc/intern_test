package com.example.currencyconverter.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.presentation.components.currency_convert.ChangeCurrencyBS
import com.example.currencyconverter.presentation.components.currency_convert.CurrencyConvertFromItem
import com.example.currencyconverter.presentation.components.currency_convert.CurrencyConvertToItem
import com.example.currencyconverter.presentation.components.currency_convert.SelectCurrencyBS
import com.example.currencyconverter.presentation.theme.Black0A0A0A
import com.example.currencyconverter.presentation.theme.Gray383838
import com.example.currencyconverter.presentation.theme.GreenB2C07A
import com.example.currencyconverter.presentation.viewmodel.CurrencyConvertViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyConverterScreen(
    modifier: Modifier = Modifier,
    viewModel: CurrencyConvertViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val sheetState = rememberModalBottomSheetState()
    Scaffold(
        containerColor = Black0A0A0A,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Black0A0A0A
                ),
                title = {
                    Text(
                        text = stringResource(R.string.currency_convert),
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.White
                        )
                    )
                }
            )
        }
    ) { padding ->
        if (state.value.isShowSelectedBs) {
            ModalBottomSheet(
                onDismissRequest = {
                    viewModel.hideSelectedBs()
                },
                sheetState = sheetState,
                containerColor = Black0A0A0A,
            ) {
                SelectCurrencyBS()
            }
        }
        if (state.value.isShowChangeBs) {
            ModalBottomSheet(
                onDismissRequest = {
                    viewModel.hideChangeBs()
                },
                sheetState = sheetState,
                containerColor = Black0A0A0A,
            ) {
                ChangeCurrencyBS()
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(padding)
                .padding(16.dp)
                .padding(bottom = 0.dp)
        ) {
            CurrencyConvertFromItem(
                currency = viewModel.getSourceCurrency(),
                value = state.value.currencyAmountInput,
                onValueChange = { value ->
                    viewModel.setCurrencyAmount(value)
                    viewModel.convertCurrency()
                },
                onCurrencyChange = { value ->
                    viewModel.updateFlag(
                        flag = 0,
                        currencyCode = value
                    )
                    viewModel.toggleChangeBs()
                }
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = Gray383838,
                        shape = CircleShape
                    )
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.SwapVert,
                    contentDescription = null,
                    tint = GreenB2C07A
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .weight(1f)
                ) {
                    items(state.value.currencyList) { currency ->
                        CurrencyConvertToItem(
                            convertedValue = viewModel.getRespectivelyConvertedData(
                                currency.code ?: ""
                            ),
                            currency = currency,
                            onCurrencyChange = { code ->
                                viewModel.toggleChangeBs()
                                viewModel.updateFlag(
                                    flag = 1,
                                    currencyCode = code
                                )
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                ElevatedButton(
                    onClick = { viewModel.toggleSelectedBottomSheet() },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                color = GreenB2C07A,
                                shape = RoundedCornerShape(20)
                            )
                            .padding(
                                top = 10.dp,
                                start = 10.dp,
                                end = 14.dp,
                                bottom = 10.dp
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                        )
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
        }
    }
}