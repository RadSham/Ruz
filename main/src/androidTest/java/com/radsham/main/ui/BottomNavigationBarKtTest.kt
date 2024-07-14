package com.radsham.main.ui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.radsham.main.BottomNavigationBar
import com.radsham.main.model.CHECKUSER_BN_ITEM
import com.radsham.main.model.HOME_BN_ITEM
import com.radsham.main.model.IAMIN_BN_ITEM
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class BottomNavigationBarKtTest {

    private lateinit var navController: TestNavHostController

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            BottomNavigationBar(navController)
        }
    }

    @Test
    fun test_bottomNavigationItems_displayed() {
        composeTestRule.onNodeWithContentDescription(HOME_BN_ITEM, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(IAMIN_BN_ITEM, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(CHECKUSER_BN_ITEM, useUnmergedTree = true)
            .assertIsDisplayed()
    }
}