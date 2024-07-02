package com.radsham.main

import com.radsham.core_api.NavScreen

sealed class BottomNavigationBarScreens(val route: String) {
    data object Home : BottomNavigationBarScreens(NavScreen.HOME_SCREEN)
    data object Search : BottomNavigationBarScreens(NavScreen.SEARCH)
    data object IamIn : BottomNavigationBarScreens(NavScreen.IAMIN)
    data object CheckUser : BottomNavigationBarScreens(NavScreen.CHECKUSER)
}