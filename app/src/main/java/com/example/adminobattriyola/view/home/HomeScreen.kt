package com.example.adminobattriyola.view.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.BotNavBar
import com.example.adminobattriyola.view.navigation.AppRoute
import com.example.adminobattriyola.view.navigation.HomeNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen() {
    val navController = rememberAnimatedNavController()
    val hideBotNavBar = remember {
        mutableStateOf(false)
    }
    val hideFAB = remember {
        mutableStateOf(false)
    }
    Scaffold(
        bottomBar = {
            AnimatedVisibility(visible = !hideBotNavBar.value) {
                BottomAppBar(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    elevation = 5.dp,
                    contentColor = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                    cutoutShape =   if(!hideBotNavBar.value) CircleShape else RectangleShape
                ) {
                    BotNavBar(navController = navController,hideFAB.value)
                }
            }
        },
        floatingActionButton = {
            AnimatedVisibility(visible = !hideBotNavBar.value) {
                if (!hideFAB.value) {
                    FloatingActionButton(
                        onClick = { navController.navigate(AppRoute.TambahObat.route) },
                        shape = CircleShape,
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onSurface) {
                        Icon(painter = painterResource(id = R.drawable.icon_add),
                            contentDescription = null,
                            modifier = Modifier

                                .size(16.dp))
                    }
                }
            }

        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue))
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier

        ) {
            HomeNavigation(navController,hideBotNavBar,hideFAB)
        }
    }
}