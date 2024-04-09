package com.radsham.ruz

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, showTopBar: MutableState<Boolean>) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        //popUp SplashScreen https://stackoverflow.com/a/68304038
        navController.navigate(NavScreen.EventsScreen.route) {
            popUpTo(NavScreen.SplashScreen.route) {
                inclusive = true
            }

        }

    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_screen_lottie))

    Box(modifier = Modifier.systemBarsPadding()) {

        Column {
            Log.d("MyLog", "SplashScreen")
            LottieAnimation(composition = composition)
        }
    }
}