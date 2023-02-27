package com.example.adminobattriyola.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.runtime.Composable

@Composable
fun Vibrate(context:Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val vibrationEffect:VibrationEffect =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect.createOneShot(100,VibrationEffect.DEFAULT_AMPLITUDE)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    vibrator.cancel()
    vibrator.vibrate(vibrationEffect)
}