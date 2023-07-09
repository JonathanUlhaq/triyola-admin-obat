package com.example.adminobattriyola.view.splashscreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adminobattriyola.view.navigation.AppRoute
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import com.example.adminobattriyola.R
import com.example.adminobattriyola.view.navigation.NavigationViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    vm:NavigationViewModel
) {

    val uiState = vm.uiState.collectAsState().value
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colors.primary
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colors.background
    )

   val autoLogin = remember {
       mutableStateOf(false)
   }

    autoLogin.value = !vm.getToken().isNullOrEmpty()
    val scalable = remember {
        Animatable(10F)
    }

    val rotates = remember {
        Animatable(136F)
    }

    val scaleIcon = remember {
        Animatable(10F)
    }

    LaunchedEffect(key1 = true, block = {
        scaleIcon.animateTo(targetValue = 70F,
            tween(1000)
        )
    } )

    LaunchedEffect(key1 = true, block = {
        scalable.animateTo( targetValue = 200F,
            animationSpec = tween(
                1000,
            )
        )
        delay(200)
        rotates.animateTo( targetValue = 0F,
            animationSpec = tween(
                1000,
            )
        )
        delay(200)
        if (!autoLogin.value) {
            navController.navigate(AppRoute.LoginScreen.route) {
                popUpTo(0)
            }
        } else {
            navController.navigate(AppRoute.HomeNavigation.route) {
                popUpTo(0)
            }
        }
    } )

    Scaffold(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .rotate(rotates.value)
                    .size(scalable.value.toInt().dp),
                painter = painterResource(id = R.drawable.logo_hospital),
                contentDescription = null,
                tint = Color.White)

            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .size(scaleIcon.value.toInt().dp),
                painter = painterResource(id = R.drawable.logo_triyola),
                contentDescription = null )
        }
    }
}