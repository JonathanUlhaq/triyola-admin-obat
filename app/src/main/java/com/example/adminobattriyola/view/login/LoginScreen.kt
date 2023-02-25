package com.example.adminobattriyola.view.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClick
import com.example.adminobattriyola.components.OutlinedTextFields
import com.example.adminobattriyola.view.navigation.AppRoute
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScree(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.background
    )

    val scrollable = rememberScrollState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .padding(it)
        ) {

            Column {
                Column(
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp, top = 14.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = stringResource(id = R.string.login_title),
                            style = MaterialTheme.typography.h2,
                            color = MaterialTheme.colors.surface
                        )

                        Spacer(modifier = Modifier.weight(1F))

                        Image(
                            painter = painterResource(id = R.drawable.login_image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(112.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = stringResource(id = R.string.login_desc),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.surface
                    )

                }

                Spacer(modifier = Modifier.height(14.dp))

                Box {
                    Surface(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .offset(y = 28.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .offset(y = 35.dp)
                                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                .verticalScroll(state = scrollable, enabled = true)

                        ) {

//                            Username
                            OutlinedTextFields(
                                viewModel.id,
                                label = stringResource(id = R.string.username),
                                R.drawable.username ,
                                eventFocus = {
//                                    hide.value = true
                                },
                                keyboardType = KeyboardType.Text
                            ) {
//                                hide.value = false
                            }
                            Spacer(modifier = Modifier.height(16.dp))
//                             Password
                            OutlinedTextFields(
                                viewModel.password,
                                label = stringResource(id = R.string.password),
                                R.drawable.password ,
                                eventFocus = {
//                                    hide.value = true
                                },
                                keyboardType = KeyboardType.Password
                            ) {
//                                hide.value = false
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            ButtonClick(backgroundColor = MaterialTheme.colors.onSurface,
                                contentColor = MaterialTheme.colors.primary ,
                                text = stringResource(R.string.masuk) ) {
                                navController.navigate(AppRoute.HomeNavigation.route) {
                                    popUpTo(0)
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .height(70.dp)
                                    .imePadding()
                            )
                        }
                    }
                }
            }
        }
    }
}