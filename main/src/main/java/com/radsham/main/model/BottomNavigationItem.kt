package com.radsham.main.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
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
                label = "Search",
                icon = Icons.Filled.Search,
                route = BottomNavigationBarScreens.Search.route
            ),
            BottomNavigationItem(
                label = "IamIn",
                icon = Icons.Filled.FavoriteBorder,
                route = BottomNavigationBarScreens.IamIn.route
            ),
            BottomNavigationItem(
                label = "CheckUser",
                icon = Icons.Filled.AccountCircle,
                route = BottomNavigationBarScreens.CheckUser.route
            )
        )
    }
}
