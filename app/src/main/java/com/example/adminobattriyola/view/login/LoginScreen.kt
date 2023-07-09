package com.example.adminobattriyola.view.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClick
import com.example.adminobattriyola.components.OutlinedTextFields
import com.example.adminobattriyola.models.UserLogin
import com.example.adminobattriyola.view.navigation.AppRoute
import com.example.adminobattriyola.view.navigation.NavigationViewModel
import com.example.adminobattriyola.widgets.tambahobat.LoadingDialog
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScree(
    navController: NavController,
    viewModel: LoginViewModel,
    navVm: NavigationViewModel
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.background
    )

    val usernameFocus = remember {
        FocusRequester()
    }
    val telepon = remember {
        mutableStateOf("")
    }

    val firstString = remember {
        mutableStateOf("")
    }

    val detectFirst = remember {
        mutableStateOf(false)
    }

    if (telepon.value.isNotEmpty()) {
        firstString.value = telepon.value.first().toString()
        detectFirst.value = firstString.value == "0"
    }
    val loading = remember {
        mutableStateOf(false)
    }
    val error = remember {
        mutableStateOf(false)
    }
    val statusError = remember {
        mutableStateOf(false)
    }
    val hidePassword = remember {
        mutableStateOf(true)
    }

    val scrollable = rememberScrollState()
    val focusManager = LocalFocusManager.current
    LoadingDialog(boolean = loading)

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
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                DisableInput()
                                Spacer(modifier = Modifier.width(8.dp))
                                OutlinedTextFields(
                                    telepon,
                                    label = "Telephone",
                                    R.drawable.phone,
                                    keyboardType = KeyboardType.Number,
                                    trailingIcon = {
                                        AnimatedVisibility(visible = (telepon.value.isNotEmpty())) {
                                            IconButton(onClick = { telepon.value = "" }) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.close_icon),
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier
                                                        .size(16.dp)
                                                )
                                            }
                                        }
                                    },
                                    isError = error.value
                                ) {
//                                hide.value = false
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
//                             Password
//                            OutlinedTextFields(
//                                viewModel.password,
//                                label = stringResource(id = R.string.password),
//                                R.drawable.password,
//                                keyboardType = KeyboardType.Password,
//                                visualTransformation = if (hidePassword.value) PasswordVisualTransformation() else VisualTransformation.None,
//                                modifier = Modifier
//                                    .focusRequester(passwordFocus)
//                                    .testTag("password"),
//                                isError = statusError.value,
//                                trailingIcon = {
//                                    IconButton(onClick = {
//                                        hidePassword.value = !hidePassword.value
//                                    }) {
//                                        Icon(
//                                            painter = painterResource(id = if (hidePassword.value) R.drawable.visibility_off else R.drawable.visibility),
//                                            contentDescription = null,
//                                            modifier = Modifier
//                                                .size(12.dp)
//                                        )
//                                    }
//                                }
//                            ) {
//                                focusManager.clearFocus()
//                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            AnimatedVisibility(visible = error.value) {
                                Text(
                                    text = "* Telepon tidak terdaftar",
                                    color = MaterialTheme.colors.error,
                                    fontSize = 10.sp
                                )
                            }
                            AnimatedVisibility(visible = detectFirst.value) {
                                Text(
                                    text = "* Isikan nomor setelah 0",
                                    color = MaterialTheme.colors.error,
                                    fontSize = 10.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            ButtonClick(
                                backgroundColor = MaterialTheme.colors.onSurface,
                                contentColor = MaterialTheme.colors.primary,
                                text = stringResource(R.string.masuk),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("masuk")
                            ) {

                                if (telepon.value.isNotEmpty() && !detectFirst.value) {
                                    loading.value = true
                                    viewModel.doLogin(telepon.value, error, loading) {
                                        navController.navigate(AppRoute.OTP.route) {
                                            popUpTo(0)
                                        }
                                    }
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

@Composable
fun DisableInput() {
    OutlinedTextField(value = "",
        onValueChange = {  },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            backgroundColor = MaterialTheme.colors.primary,
            disabledBorderColor = MaterialTheme.colors.onSurface.copy(0.8F),
            disabledLabelColor = MaterialTheme.colors.onSurface.copy(0.8F),
            trailingIconColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White
        ),
        label = {
            Text(
                text = "+62",
                style = MaterialTheme.typography.h1,
                fontSize = 12.sp
            )
        },
        shape = RoundedCornerShape(12.dp),
        enabled = false,
        modifier = Modifier
            .width(60.dp)
    )
}