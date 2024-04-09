package com.radsham.ruz

sealed class NavScreen(var route: String) {
    object SplashScreen : NavScreen("SplashScreen")
    object EventsScreen : NavScreen("EventsScreen")
}
