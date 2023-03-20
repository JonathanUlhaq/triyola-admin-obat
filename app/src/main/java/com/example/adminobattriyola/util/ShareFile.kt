package com.example.adminobattriyola.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File

fun shareFile(context: Context, file: File) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "application/pdf"
    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    context.startActivity(
        Intent.createChooser(intent, "Share Ajuan")
    )
}