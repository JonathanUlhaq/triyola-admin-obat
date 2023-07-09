package com.example.adminobattriyola.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.adminobattriyola.R

@Composable
fun AccountDropDown(
    boolean:MutableState<Boolean>,
    onClick:() -> Unit
) {
    DropdownMenu(expanded = boolean.value , onDismissRequest = { boolean.value = false }, modifier = Modifier.background(
        Color.White)) {
        DropdownMenuItem(onClick = {
            boolean.value = false
            onClick.invoke() }) {
            Text(text = "Logout",
                color = Color.Black.copy(0.6F),
                fontSize = 12.sp,
                style = MaterialTheme.typography.caption)
        }
    }
}


@Composable
fun LogoutConfirm(
    boolean: MutableState<Boolean>,
    cancel: () -> Unit,
    dismiss: () -> Unit,
    confirm: () -> Unit
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
            boolean.value = false
            dismiss.invoke()
        }) {
            DialogContent(cancel = {
                cancel.invoke()
            }) {
                confirm.invoke()
            }
        }
    }
}

@Composable
fun DialogContent(
    cancel: () -> Unit,
    confirm: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.onSurface,
        contentColor = MaterialTheme.colors.surface,

        ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, end = 24.dp, start = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Apakah anda yakin akan logout ?",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )

            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.error.copy(0.2F)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = { cancel.invoke() }) {
                    Text(
                        text = stringResource(R.string.tidak),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.surface.copy(0.4F)
                    )
                }
                TextButton(onClick = { confirm.invoke() }) {
                    Text(
                        text = stringResource(R.string.iya),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}