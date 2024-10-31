package com.example.currencyconverter.presentation.components.home_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.data.models.Currency
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.presentation.theme.Gray222222
import com.example.currencyconverter.presentation.theme.Gray383838
import com.example.currencyconverter.presentation.theme.Gray828282
import com.example.currencyconverter.presentation.theme.GreenB2C07A

@Composable
fun FavoriteCurrencyRateItem(
    currencyDetail: CurrencyDetail,
    currencyRate: Currency,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(
                color = Gray222222,
                shape = RoundedCornerShape(12)
            )
            .size(width = 120.dp, height = 125.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(26.dp)
                    .background(color = Gray383838)
            ) {
                Text(
                    text = currencyDetail.symbol ?: "",
                    fontSize = 13.sp,
                    color = GreenB2C07A,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = currencyDetail.code ?: "",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.White
                ),
            )
        }
        Column {
            Text(
                text = currencyDetail.namePlural ?: "",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.W400,
                    color = Gray828282
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
            Box(
                modifier = Modifier
                    .background(
                        color = GreenB2C07A,
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Text(
                    text = currencyRate.value.toString(),
                    style = TextStyle(

                        fontSize = 13.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.Black
                    ),
                )
            }
        }
    }
}