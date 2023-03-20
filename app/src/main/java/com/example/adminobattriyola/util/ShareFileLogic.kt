package com.example.adminobattriyola.util

import android.content.Context
import android.os.Environment
import com.example.adminobattriyola.MainActivity
import java.io.File

fun shareFileLogic(context: Context, fileName: String, url: String, activity: MainActivity) {
    val file = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
        fileName
    )
    if (file.exists()) {
        shareFile(context, file)
    } else {
        downloadFile(fileName, url, activity)
        shareFile(context, file)
    }
}