package com.radsham.splash.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.radsham.core_api.NavScreen
import com.radsham.splash.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        //popUp SplashScreen https://stackoverflow.com/a/68304038
        navController.navigate(NavScreen.HOME_SCREEN) {
            popUpTo(NavScreen.SPLASH_SCREEN) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_screen_lottie))

    Box(modifier = Modifier.systemBarsPadding()) {
        Column {
            LottieAnimation(composition = composition)
        }
    }
}