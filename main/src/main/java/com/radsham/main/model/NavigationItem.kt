package com.radsham.main.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.radsham.main.BottomNavigationBarScreens

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
                route = BottomNavigationBarScreens.Home.route
            ),
            BottomNavigationItem(
                label = "Account",
                icon = Icons.Filled.AccountCircle,
                route = BottomNavigationBarScreens.Account.route
            ),
            BottomNavigationItem(
                label = "SignIn",
                icon = Icons.Filled.Add,
                route = BottomNavigationBarScreens.SignIn.route
            ),
        )
    }
}
