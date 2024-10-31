package com.example.currencyconverter.presentation.components.currency_convert

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.presentation.theme.Gray222222
import com.example.currencyconverter.presentation.theme.Gray383838
import com.example.currencyconverter.presentation.theme.Gray444444
import com.example.currencyconverter.presentation.theme.Gray828282
import com.example.currencyconverter.presentation.theme.GreenB2C07A

// the source currency item
@Composable
fun CurrencyConvertFromItem(
    value: String,
    currency: CurrencyDetail,
    onValueChange: (String) -> Unit,
    onCurrencyChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Gray222222, shape = RoundedCornerShape(16))
            .padding(horizontal = 14.dp)
            .padding(top = 18.dp, bottom = 28.dp)
    ) {
        Text(
            text = stringResource(R.string.exchange_from),
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W400, color = Gray828282)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BasicTextField(
                value = value,
                onValueChange = { value ->
                    onValueChange(value)
                    Log.d("CurrencyConvertViewModel", value)
                },
                textStyle = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.White
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                cursorBrush = SolidColor(Color.White),
                keyboardActions = KeyboardActions(
                    onDone = {
                        Log.d("CurrencyConvertScreen", "currency convert submit")
                    }
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        onCurrencyChange(currency.code ?: "")
                    }
                    .background(color = Gray444444, shape = RoundedCornerShape(60))
                    .padding(3.dp)

            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(26.dp)
                        .background(color = Gray383838)
                ) {
                    Text(
                        text = currency.symbol ?: "",
                        fontSize = 12.sp,
                        color = GreenB2C07A,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(24.dp)
                ) {
                    Text(
                        text = currency.code ?: "",
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                )
            }
        }
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(0.65.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color.Transparent, GreenB2C07A, Color.Transparent),
                        startX = 0f,
                        endX = Float.POSITIVE_INFINITY // This creates the gradient effect
                    )
                )
        )
    }
}