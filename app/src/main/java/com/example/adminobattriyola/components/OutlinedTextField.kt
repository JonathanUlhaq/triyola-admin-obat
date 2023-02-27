package com.example.adminobattriyola.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedTextFields(
    value: MutableState<String>,
    label: String,
    icon: Int,
    isError:Boolean = false,
    keyboardType: KeyboardType,
    color: Color = Color.White,
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(value = value.value,
        onValueChange = { value.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = color,
            backgroundColor = Color.Transparent,
            unfocusedBorderColor = color.copy(0.8F),
            focusedBorderColor = color,
            leadingIconColor = color,
            focusedLabelColor = color,
            unfocusedLabelColor = color,
            cursorColor = color,
            errorBorderColor = MaterialTheme.colors.error,
            errorCursorColor = MaterialTheme.colors.error,
            errorLabelColor = MaterialTheme.colors.error,
            errorLeadingIconColor = MaterialTheme.colors.error,
            errorTrailingIconColor = MaterialTheme.colors.error
        ),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.h1,
                fontSize = 12.sp
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )   
        },
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()
        ,
        shape = RoundedCornerShape(12.dp),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType),
        singleLine = true,
        isError = false
    )
}

@Composable
fun OutlinedTextFieldsItem(
    value: String,
    onValueChange:MutableState<String>,
    label: String,
    icon: Int,
    keyboardType: KeyboardType,
    color: Color = Color.White
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(value = value,
        onValueChange = { onValueChange.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = color,
            backgroundColor = Color.Transparent,
            unfocusedBorderColor = color.copy(0.8F),
            focusedBorderColor = color,
            leadingIconColor = color,
            focusedLabelColor = color,
            unfocusedLabelColor = color,
            cursorColor = color,
            errorBorderColor = MaterialTheme.colors.error,
            errorCursorColor = MaterialTheme.colors.error,
            errorLabelColor = MaterialTheme.colors.error,
            errorLeadingIconColor = MaterialTheme.colors.error,
            errorTrailingIconColor = MaterialTheme.colors.error
        ),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.h1,
                fontSize = 12.sp
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()
        ,
        shape = RoundedCornerShape(12.dp),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType),
        singleLine = true,
        isError = false
    )
}

