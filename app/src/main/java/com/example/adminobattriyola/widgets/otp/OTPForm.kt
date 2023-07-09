package com.example.aplikasiklinik.widget.otp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OTPForm(
    event:()->Unit,
    string: (String) -> Unit
) {
    var otpValue by remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = FocusRequester()
    val context = LocalContext.current


    BasicTextField(
        value = otpValue,
        onValueChange = {
            if (it.length <= 4) {
                otpValue = it
                string.invoke(otpValue)
            }
            if (it.length >= 4) {
                keyboardController?.hide()
                event.invoke()
            Log.d("OTPNYA MAS ",otpValue)
            }

        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.body1.copy(Color.Black),
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                repeat(4) { index ->
                    val char = when {
                        index >= otpValue.length -> ""
                        else -> otpValue[index].toString()
                    }

                    Text(
                        text = char,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .width(60.dp)
                            .background(MaterialTheme.colors.background)
                            .padding(top = 12.dp, bottom = 12.dp, start = 4.dp, end = 4.dp),
                        style = MaterialTheme.typography.h2,
                        fontSize = 30.sp,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        },
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) {
                    keyboardController?.show()
                }
            }
            .imePadding(),
        keyboardActions = KeyboardActions {
            keyboardController?.hide()
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

