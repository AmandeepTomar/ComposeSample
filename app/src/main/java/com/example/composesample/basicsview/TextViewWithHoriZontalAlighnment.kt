package com.example.composesample.basicsview

import android.graphics.Paint.Style
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composesample.R


@Composable
fun TextViewWithHorizontal() {
    LazyColumn{

    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(10.dp, Color.Red)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Text1")
        Spacer(Modifier.height(10.dp))
        Text(text = "Text2", modifier = Modifier.align(Alignment.Start))
    }
}

@Composable
fun TextStylingOfView() {
    val fontFamily = FontFamily(
        Font(R.font.roboto_black, FontWeight.Black),
        Font(R.font.roboto_blackitalic, FontWeight.Black),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_italic, FontWeight.Medium),
        Font(R.font.roboto_light, FontWeight.Light),
    )
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Red,
                    fontSize = 32.sp,
                    fontFamily = fontFamily
                )
            ) {
                append("He")
            }
            append("llo")
            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 32.sp,
                    fontFamily = fontFamily,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Jet")
            }
            append("pack")
            withStyle(
                style = SpanStyle(
                    color = Color.Yellow,
                    fontSize = 32.sp,
                    fontFamily = fontFamily,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("C")
            }
            append("ompose")
        },
        fontFamily = fontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.Black
    )
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    Column {
        TextStylingOfView()
        Spacer(modifier = Modifier.height(16.dp))
        TextViewWithHorizontal()
        Spacer(modifier = Modifier.height(16.dp))
        val paunter = painterResource(id = R.drawable.ab2_quick_yoga)
        ImageCard(
            paunter,
            contentDescription = "This is Image With the Description",
            "This is Image With the Description"
        )
    }
}