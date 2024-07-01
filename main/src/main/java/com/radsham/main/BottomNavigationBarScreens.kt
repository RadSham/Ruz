package com.radsham.main

import com.radsham.core_api.NavScreen

sealed class BottomNavigationBarScreens(val route: String) {
    data object Home : BottomNavigationBarScreens(NavScreen.HOME_SCREEN)
    data object Account : BottomNavigationBarScreens(NavScreen.ACCOUNT)
    data object SignIn : BottomNavigationBarScreens(NavScreen.SIGN_IN)
}