package com.radsham.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.radsham.core_api.NavScreen
import com.radsham.main.model.BottomNavigationItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    var navigationSelectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
            val selected = currentDestination?.hierarchy?.any {
                it.route == navigationItem.route || it.route?.startsWith(
                    navigationItem.route
                ) ?: false
            } == true
            NavigationBarItem(
                selected = selected,
                icon = {
                    Icon(
                        if (selected) navigationItem.iconFilled else navigationItem.iconOutlined,
                        contentDescription = navigationItem.label
                    )
                },
                onClick = {
                    navigationSelectedItem = index
                    navController.navigate(navigationItem.route) {
                        popUpTo(NavScreen.HOME_SCREEN) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}