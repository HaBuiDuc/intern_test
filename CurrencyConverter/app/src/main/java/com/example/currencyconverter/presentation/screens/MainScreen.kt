package com.example.currencyconverter.presentation.screens

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.R
import com.example.currencyconverter.common.LoadingDataState
import com.example.currencyconverter.common.NetworkConnectionState
import com.example.currencyconverter.presentation.exception.UnavailableInternetScreen
import com.example.currencyconverter.presentation.navigation.MainGraph
import com.example.currencyconverter.presentation.navigation.ScreenList
import com.example.currencyconverter.presentation.theme.Black0A0A0A
import com.example.currencyconverter.presentation.theme.Gray222222
import com.example.currencyconverter.presentation.theme.GreenB2C07A
import com.example.currencyconverter.presentation.viewmodel.CurrencyViewModel
import com.example.currencyconverter.utils.rememberConnectivityState

@Composable
fun MainScreen(
    currencyViewModel: CurrencyViewModel = hiltViewModel()
) {
    val connectionState by rememberConnectivityState()

    val isConnected by remember(connectionState) {
        derivedStateOf {
            connectionState === NetworkConnectionState.Available
        }
    }

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
            )
        },
        containerColor = Black0A0A0A,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = Modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
                .systemBarsPadding()
        ) {
            if (isConnected) {
                when (currencyViewModel.isGetDataSuccess.value) {
                    LoadingDataState.Loading -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    LoadingDataState.Success -> {
                        MainGraph(
                            navHostController = navController,
                        )
                    }

                    LoadingDataState.Failure -> {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(R.drawable.disconnect),
                                contentDescription = null
                            )
                            Text(
                                stringResource(R.string.can_not_get_data_instruct),
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
                            Spacer(modifier = Modifier.height(10.dp))
                            ElevatedButton(
                                contentPadding = PaddingValues(0.dp),
                                onClick = { currencyViewModel.loadingData() },
                                shape = RoundedCornerShape(20),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GreenB2C07A
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.reload),
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
            } else {
                UnavailableInternetScreen()
            }

        }
    }
}


@Composable
private fun BottomBar(
    navController: NavHostController,
) {
    val screens = listOf(
        ScreenList.HomeScreen,
        ScreenList.CurrencyConverterScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
            containerColor = Gray222222,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        ) {
            screens.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true,
                    onClick = {
                        navController.navigate(screen.route)
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            text = screen.title,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W500
                            )
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = GreenB2C07A,
                        selectedTextColor = GreenB2C07A,
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .padding(3.dp)
                )
            }
        }
    }
}