package com.example.currencyconverter.presentation.components.home_page

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.currencyconverter.R
import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.presentation.theme.Gray828282
import com.example.currencyconverter.presentation.theme.GreenB2C07A
import com.example.currencyconverter.presentation.viewmodel.HomePageViewModel
import java.text.DecimalFormat

@Composable
fun CurrencyRateItem(
    currency: Currency,
    currencyDetail: CurrencyDetail,
    modifier: Modifier = Modifier,
    homePageViewModel: HomePageViewModel = hiltViewModel(),
) {
    Box(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    error = painterResource(R.drawable.default_img),
                    model = homePageViewModel.parseFlagUrl(
                        currencyDetail = currencyDetail
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(38.dp)
                        .border(1.dp, GreenB2C07A, shape = CircleShape)

                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = currency.code,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = currencyDetail.name ?: "",
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.W400,
                            color = Gray828282
                        )
                    )
                }
            }
            val decimalFormat = DecimalFormat("#.####")
            val roundedValue = decimalFormat.format(currency.value)
            Text(
//                text = "${currencyDetail.symbol?.lowercase()}  $roundedValue",
                text = "${currencyDetail.symbol?.lowercase()}  ${currency.value}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.White
                )
            )
        }
    }
}