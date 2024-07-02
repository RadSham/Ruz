package com.radsham.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.radsham.core_api.FeatureApi
import com.radsham.core_api.listener.ShowBottomNavigationBarListener
import com.radsham.main.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var featureProvider: Map<String, @JvmSuppressWildcards FeatureApi>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    var bottomBarShowBool by remember { mutableStateOf(false) }
                    val showBottomNavigationBarListener = object : ShowBottomNavigationBarListener {
                        override fun showBar(showBarBoolean: Boolean) {
                            bottomBarShowBool = showBarBoolean
                        }
                    }
                    Scaffold(
                        bottomBar = { if (bottomBarShowBool) BottomNavigationBar(navController) }
                    ) { paddingValues ->
                        SetupNavGraph(featureProvider, navController, paddingValues, showBottomNavigationBarListener)
                    }
                }
            }
        }
    }
}
