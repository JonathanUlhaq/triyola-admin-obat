package com.example.adminobattriyola.view.pengajuan.riwayat.detail

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adminobattriyola.MainActivity
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.BackIcon
import com.example.adminobattriyola.components.TextTitle
import com.example.adminobattriyola.util.downloadFile
import com.example.adminobattriyola.util.shareFileLogic
import com.example.adminobattriyola.util.shimeringColor
import com.example.adminobattriyola.widgets.detailpengajuan.ItemTitle
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

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
                Spacer(modifier = Modifier.height(24.dp))
                ItemTitle()
                Spacer(modifier = Modifier.height(16.dp))
                Surface(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp,  bottom = 16.dp),
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








