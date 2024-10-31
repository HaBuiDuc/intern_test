package com.example.currencyconverter.presentation.components.currency_convert

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
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
import com.example.currencyconverter.data.models.CurrencyDetail
import com.example.currencyconverter.presentation.theme.GreenB2C07A
import com.example.currencyconverter.presentation.viewmodel.SelectedCurrencyViewModel

// list item of selected currency bottom sheet
@Composable
fun SelectedCurrencyItem(
    currencyDetail: CurrencyDetail,
    modifier: Modifier = Modifier,
    selectedCurrencyViewModel: SelectedCurrencyViewModel = hiltViewModel()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                currencyDetail.code?.let {
                    selectedCurrencyViewModel.onSelectedItem(it)
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                error = painterResource(R.drawable.default_img),
                model = selectedCurrencyViewModel.parseFlag(currencyDetail),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(34.dp)
                    .border(1.dp, GreenB2C07A, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "${currencyDetail.code} - ${currencyDetail.name}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.White
                )
            )
        }
        if (selectedCurrencyViewModel.isSelectedItem(currencyDetail.code ?: "")) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = GreenB2C07A
            )
        }
    }
}