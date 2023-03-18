package com.example.adminobattriyola.view.pengajuan.riwayat.detail

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adminobattriyola.MainActivity
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.BackIcon
import com.example.adminobattriyola.components.TextTitle
import com.example.adminobattriyola.widgets.tambahobat.IconFAQ
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import java.io.File

@Composable
fun DetailPengajuan(
    navController: NavController,
    activity: MainActivity
) {
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote("https://myreport.altervista.org/Lorem_Ipsum.pdf"),
        isZoomEnable = true
    )
    val context = LocalContext.current
    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0F,
        targetValue = 1000F,
        animationSpec = infiniteRepeatable(
            tween(1000),
            RepeatMode.Reverse
        )
    )
    val brush = Brush.linearGradient(
        colors = shimeringColor(),
        start = Offset(10F, 10F),
        end = Offset(translateAnimation, translateAnimation)
    )

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = {
                    TextTitle("Detail Pengajuan")
                },
                navigationIcon = {
                    BackIcon(navController)
                },
                backgroundColor = MaterialTheme.colors.onPrimary,
                elevation = 0.dp,
                actions = {
                    if (pdfState.file != null) {
                        IconButton(onClick = {
                            downloadFile(
                                "Coba Ajuan.pdf",
                                "https://myreport.altervista.org/Lorem_Ipsum.pdf",
                                activity
                            )
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.download_pdf_icon),
                                contentDescription = null,
                                tint = MaterialTheme.colors.onSurface,
                                modifier = Modifier
                                    .size(18.dp)
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            if (pdfState.file != null) {
                FloatingActionButton(
                    onClick = {
                        shareFileLogic(
                            context,
                            "Coba Ajuan.pdf",
                            "https://myreport.altervista.org/Lorem_Ipsum.pdf",
                            activity
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(4.dp),
                    shape = CircleShape
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.send_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
            }
        }

    ) {
        Surface(
            modifier = Modifier
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                        .background(MaterialTheme.colors.onPrimary)
                        .height(30.dp)
                )
                Surface(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 26.dp, bottom = 26.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colors.background
                ) {
                    VerticalPDFReader(
                        state = pdfState,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(brush)
                    )
                }
            }
        }
    }
}


fun shimeringColor(): List<Color> {
    val ShimmerColorShades = listOf(
        Color.LightGray.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f)
    )
    return ShimmerColorShades
}

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

fun downloadFile(fileName: String, url: String, activity: MainActivity) {
    val request = DownloadManager.Request(Uri.parse(url))
        .setTitle(fileName)
        .setDescription("PDF dari $fileName")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        .setAllowedOverRoaming(true)
        .setAllowedOverMetered(true)
        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)

    val downloadManager = activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)
}

fun shareFile(context: Context,file:File) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "application/pdf"
    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    context.startActivity(
        Intent.createChooser(intent, "Share Ajuan")
    )
}