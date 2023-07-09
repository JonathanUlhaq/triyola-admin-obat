package com.example.adminobattriyola.widgets.daftarobat

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimerLoading() {
    val gradient = listOf(
        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnimation = transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // duration for the animation
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(x = translateAnimation.value,
            y = translateAnimation.value)
    )

    Surface(
        color = Color.Transparent,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, bottom = 14.dp, start = 18.dp, end = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .size(width = 60.dp, height = 60.dp)
                .background(brush, RoundedCornerShape(15.dp)))
            Box(modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .size(width = 60.dp, height = 60.dp)
                .background(brush, RoundedCornerShape(15.dp)))
        }
    }
}