package com.example.adminobattriyola.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonClick(
    backgroundColor: Color,
    contentColor:Color,
    text:String,
    click:()->Unit
) {
    Button(
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .imePadding(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =  backgroundColor,
            contentColor = contentColor
        ),
        onClick = { click.invoke() }) {
        Text(
            text = text,
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .padding(4.dp)
        )
    }
}