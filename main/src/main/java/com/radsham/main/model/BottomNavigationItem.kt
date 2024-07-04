package com.radsham.main.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.radsham.core_api.NavScreen

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = NavScreen.HOME_SCREEN
            ),
            BottomNavigationItem(
                label = "IamIn",
                icon = Icons.Filled.FavoriteBorder,
                route = NavScreen.IAMIN
            ),
            BottomNavigationItem(
                label = "CheckUser",
                icon = Icons.Filled.AccountCircle,
                route = NavScreen.CHECKUSER
            )
        )
    }
}
