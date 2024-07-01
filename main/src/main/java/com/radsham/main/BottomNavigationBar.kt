package com.radsham.main

import android.util.Log
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.radsham.main.model.BottomNavigationItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    var navigationSelectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                /*label = {
                    Text(navigationItem.label)
                },
                alwaysShowLabel = false,*/
                icon = {
                    Icon(
                        navigationItem.icon,
                        contentDescription = navigationItem.label
                    )
                },
                onClick = {
                    navigationSelectedItem = index
                    navController.navigate(navigationItem.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(BottomNavigationBarScreens.Home.route) {
                            saveState = true
                        }
                    }
                    Log.d("MyLog", navController.graph.findStartDestination().route.toString())
                }
            )
        }
    }
}