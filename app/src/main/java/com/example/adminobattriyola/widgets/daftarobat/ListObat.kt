package com.example.adminobattriyola.widgets.daftarobat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListObat(
    name: String,
    type: String,
    amount: Int,
    boolean: Boolean,
    multipleSelect:Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {

    val colorSelected by animateColorAsState(targetValue = if (boolean) MaterialTheme.colors.secondary.copy(0.4F) else MaterialTheme.colors.onBackground)

    Surface(
        color = Color.Transparent,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .combinedClickable(
                onClick = { onClick.invoke() },
                onLongClick = { onLongClick.invoke() }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (modifier = Modifier
                .padding(top = 14.dp, bottom = 14.dp, start = 18.dp, end = 18.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = type,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 14.dp, start = 18.dp, end = 18.dp)
            ) {
                Text(
                    text = stringResource(R.string.jumlah_text),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colors.onPrimary,
                        contentColor = MaterialTheme.colors.onSurface
                    ) {
                        Text(
                            text = amount.toString(),
                            style = MaterialTheme.typography.h1,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "box",
                        style = MaterialTheme.typography.body2,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }

            AnimatedVisibility(visible = multipleSelect) {
                Box(modifier = Modifier
                    .size(80.dp)
                    .background(colorSelected))
            }
        }
    }
}