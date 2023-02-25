package com.example.adminobattriyola.view.tambahobat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adminobattriyola.R

@Composable
fun TambahObatScreen(navController: NavController) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.tambah_obat),
                        style = MaterialTheme.typography.h1,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(painter = painterResource(id = R.drawable.back_arrow),
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                                .size(12.dp))
                    }
                },
                backgroundColor = MaterialTheme.colors.onPrimary,
                elevation = 0.dp
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it),
            color = Color.Transparent
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                        .background(MaterialTheme.colors.onPrimary)
                        .height(80.dp)
                )

            }
        }
    }
}