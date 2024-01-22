package com.example.composesample.basicsview

import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp


@Composable
fun ImageCard(painter: Painter, contentDescription: String, title: String) {

    Card(
        modifier = Modifier.fillMaxWidth(.5f),
        shape = RoundedCornerShape(16.dp),
        elevation = 12.dp
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(painter = painter, contentDescription = contentDescription, contentScale = ContentScale.Crop)
        }
        Box(
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, Color.Black), startY = 300f
                )
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(text = title)
        }
    }
}