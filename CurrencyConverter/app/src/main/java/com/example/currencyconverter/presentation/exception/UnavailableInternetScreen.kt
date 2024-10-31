package com.example.currencyconverter.presentation.exception

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R
import com.example.currencyconverter.presentation.theme.GreenB2C07A

@Composable
fun UnavailableInternetScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.disconnect),
            contentDescription = null
        )
        Text(
            stringResource(R.string.internet_unavailable_instruction),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                color = GreenB2C07A,
                textAlign = TextAlign.Center,
            ),
            maxLines = 2,
            modifier = Modifier
                .width(400.dp)
        )
    }
}