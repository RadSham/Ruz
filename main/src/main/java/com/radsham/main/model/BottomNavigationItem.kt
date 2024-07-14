package com.radsham.main.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.radsham.core_api.NavScreen

const val HOME_BN_ITEM = "Home"
const val IAMIN_BN_ITEM = "IamIn"
const val CHECKUSER_BN_ITEM = "CheckUser"

data class BottomNavigationItem(
    val label: String = "",
    val iconFilled: ImageVector = Icons.Filled.Home,
    val iconOutlined: ImageVector = Icons.Outlined.Home,
    val route: String = "",
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = HOME_BN_ITEM,
                iconFilled = Icons.Filled.Home,
                iconOutlined = Icons.Outlined.Home,
                route = NavScreen.HOME_SCREEN
            ),
            BottomNavigationItem(
                label = IAMIN_BN_ITEM,
                iconFilled = Icons.Filled.Favorite,
                iconOutlined = Icons.Outlined.FavoriteBorder,
                route = NavScreen.IAMIN_SCREEN
            ),
            BottomNavigationItem(
                label = CHECKUSER_BN_ITEM,
                iconFilled = Icons.Filled.AccountCircle,
                iconOutlined = Icons.Outlined.AccountCircle,
                route = NavScreen.CHECKUSER_SCREEN
            )
        )
    }
}
