package com.example.adminobattriyola.view.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.adminobattriyola.view.daftarobat.DaftarObatScreen
import com.example.adminobattriyola.view.daftarobat.DaftarObatViewModel
import com.example.adminobattriyola.view.tambahobat.TambahObatScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavigation(
    navController: NavHostController,
    hideBotNavBar:MutableState<Boolean>
) {
    val systemUiController = rememberSystemUiController()
    val daftarObat = hiltViewModel<DaftarObatViewModel>()
    AnimatedNavHost(navController = navController, startDestination = AppRoute.DaftarObat.route) {
        composable(AppRoute.DaftarObat.route,
            enterTransition = {fadeIn(tween(700))}
        ) {
            systemUiController.setStatusBarColor(
                color = MaterialTheme.colors.primaryVariant
            )

            systemUiController.setNavigationBarColor(
                color = MaterialTheme.colors.background
            )
            hideBotNavBar.value = false
            DaftarObatScreen(daftarObat)
        }

        composable(AppRoute.TambahObat.route,
            enterTransition = {fadeIn(tween(700))}
        ) {
            systemUiController.setStatusBarColor(
                color = MaterialTheme.colors.primaryVariant
            )

            systemUiController.setNavigationBarColor(
                color = MaterialTheme.colors.background
            )
            hideBotNavBar.value = true
            TambahObatScreen(navController)
        }

    }
}