package com.example.adminobattriyola.widgets.riwayatobat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R

@Composable
fun RiwayatObatContent(boolean: Boolean, contentDetailTransaksi:@Composable () -> Unit, onClick:() -> Unit) {

    val animateIcon by animateIntAsState(targetValue = if (boolean) R.drawable.arrow_down else R.drawable.arrow_right)

    Surface(
        color = Color.Transparent,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .clickable {
                onClick.invoke()
            }
    ) {
        Column (
            modifier = Modifier
                .padding(22.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Januari 2023",
                    style = MaterialTheme.typography.h1,
                    fontSize = 14.sp ,
                    color = MaterialTheme.colors.onPrimary
                )

                Icon(painter = painterResource(id = animateIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(12.dp),
                    tint = MaterialTheme.colors.onPrimary)

            }
            AnimatedVisibility(visible = boolean) {
                Column {
                    Spacer(modifier = Modifier.height(14.dp))
                    Divider(
                        color = MaterialTheme.colors.surface.copy(0.2F),
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    contentDetailTransaksi.invoke()
                }
            }
        }
    }
}