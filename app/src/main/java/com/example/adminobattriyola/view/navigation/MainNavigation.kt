package com.example.adminobattriyola.view.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.adminobattriyola.MainActivity
import com.example.adminobattriyola.view.home.HomeScreen
import com.example.adminobattriyola.view.login.LoginScree
import com.example.adminobattriyola.view.login.LoginViewModel
import com.example.adminobattriyola.view.splashscreen.SplashScreen
import com.example.aplikasiklinik.view.otp.OTPViewModel
import com.example.aplikasiklinik.view.otp.OtpScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation(
    activity: MainActivity
) {
    val navController = rememberAnimatedNavController()

    val login = remember {
        mutableStateOf(false)
    }

    val boardingExit = remember {
        mutableStateOf(false)
    }

    val loginViewmodel = hiltViewModel<LoginViewModel>()
    val navViewmodel = hiltViewModel<NavigationViewModel>()
    val otpViewmodel = hiltViewModel<OTPViewModel>()

    AnimatedNavHost(navController = navController, startDestination = AppRoute.SplashScreen.route) {
        composable(AppRoute.SplashScreen.route,
//            enterTransition = {
//                fadeIn(tween(300))
//            },
//            exitTransition = {
//                slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(300))
//            }
        ) {
            SplashScreen(navController = navController,navViewmodel)
        }

        composable(AppRoute.OTP.route) {
            OtpScreen(navController = navController , viewModel = otpViewmodel )
        }

        composable(AppRoute.LoginScreen.route,
//            enterTransition = {
//                slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(300))
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    towards = if (boardingExit.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Down,
//                    tween(300)
//                )
//            }
        ) {
            LoginScree(navController = navController, viewModel = loginViewmodel,navViewmodel)
        }

        composable(AppRoute.HomeNavigation.route,
//            enterTransition = {
//                slideIntoContainer(towards =  AnimatedContentScope.SlideDirection.Left ,tween(300))
//            },
//            exitTransition = {
//                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Left,tween(300))
//            }
        ) {
            HomeScreen(activity)
        }
    }
}