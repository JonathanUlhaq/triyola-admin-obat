package com.example.adminobattriyola

import androidx.activity.viewModels
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.adminobattriyola.view.login.LoginScree
import com.example.adminobattriyola.view.login.LoginViewModel
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering.Context

@HiltAndroidTest
class ComposeTesting {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule(MainActivity::class.java)





    @Before
    fun setup () {
        hiltRule.inject()

    }

    @Test
    fun LoginToHomeScreen() {
        composeRule.onNodeWithTag("splashscreen").assertIsDisplayed()
        composeRule.onNodeWithTag("loncat").performClick()

        composeRule.onNodeWithTag("username").performTextInput("aezakmi")
        composeRule.onNodeWithTag("password").performTextInput("baguvix")
        composeRule.onNodeWithTag("username").assertTextContains("aezakmi")
        composeRule.onNodeWithTag("masuk").performClick()
    }
}