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
import androidx.compose.ui.unit.sp

@Composable
fun ButtonClick(
    backgroundColor: Color,
    contentColor:Color,
    text:String,
    modifier: Modifier = Modifier,
    boolean:Boolean = true,
    roundedeCorners:Int = 20,
    fontSizes:Int = 16,
    click:()->Unit
) {
    Button(
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(roundedeCorners.dp),
        modifier = modifier
            .imePadding(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =  backgroundColor,
            contentColor = contentColor
        ),
        enabled = boolean,
        onClick = { click.invoke() }) {
        Text(
            text = text,
            style = MaterialTheme.typography.h2,
            fontSize = fontSizes.sp,
            modifier = Modifier
                .padding(4.dp)
        )
    }
}

@Composable
fun ButtonClickSecond(
    backgroundColor: Color,
    contentColor:Color,
    text:String,
    modifier: Modifier = Modifier,
    click:()->Unit
) {
    Button(
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .imePadding(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =  backgroundColor,
            contentColor = contentColor
        ),
        onClick = { click.invoke() }) {
        Text(
            text = text,
            style = MaterialTheme.typography.h1,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(2.dp)
        )
    }
}