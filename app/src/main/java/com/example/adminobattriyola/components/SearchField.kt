package com.example.adminobattriyola.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    value: MutableState<String>,
    icon:Int,
    label:String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = value.value, onValueChange = { string ->
        value.value = string
    },

    textStyle = MaterialTheme.typography.body2,
    leadingIcon = {
        Icon(painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp))
    },
    label = {
        Text(text = label,
            style = MaterialTheme.typography.body2)
    },
    shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.onPrimary,
            leadingIconColor = MaterialTheme.colors.onSurface,
            unfocusedLabelColor = MaterialTheme.colors.onSurface,
            focusedLabelColor = MaterialTheme.colors.onSurface,
            focusedIndicatorColor = MaterialTheme.colors.onSurface
        ),
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions {
            keyboardController?.hide()
        }
    )
}