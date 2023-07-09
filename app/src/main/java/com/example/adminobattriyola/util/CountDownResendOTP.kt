package com.example.adminobattriyola.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.adminobattriyola.widgets.tambahobat.LoadingDialog
import com.example.aplikasiklinik.view.otp.OTPViewModel
import kotlinx.coroutines.delay

@Composable
fun CountDownResendOTP(timer:Int,modifier: Modifier = Modifier,vm:OTPViewModel) {
    val isLoading = remember {
        mutableStateOf(false)
    }
    LoadingDialog(boolean = isLoading)
    val countTime = remember { mutableStateOf(timer) }
    LaunchedEffect(key1 = Unit, block = {
        while (countTime.value > 0) {
            delay(1000)
            countTime.value --
        }
    } )
    Surface(
        modifier = modifier,
        color = Color.Transparent
    ) {
        if (countTime.value == 0) {
            Text(
                text = "Kirim ulang",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.background,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable {
                        vm.doLogin(vm.getTelepon()!!, loading = isLoading) {
                            countTime.value = 30
                        }
                    }
            )
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(backgroundColor = MaterialTheme.colors.background, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = if (countTime.value > 9 )"00:${countTime.value}" else "00:0${countTime.value}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.background,
                )
            }
        }
    }
}