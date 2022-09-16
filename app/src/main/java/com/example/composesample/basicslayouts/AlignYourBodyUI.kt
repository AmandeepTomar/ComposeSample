package com.example.composesample.basicslayouts

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesample.R

class AlignYourBodyUI {

    @Composable
    fun alignYouBodyUI(modifier: Modifier, @StringRes name: Int, @DrawableRes drawableRes: Int) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = drawableRes),
                contentDescription = "ab1_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                stringResource(id = name),
                color =MaterialTheme.colors.primary,
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)

            )
        }
    }

    @Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES)
    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
    @Composable
    fun alignYourBodyPreview() {
        val name = R.string.ab1_inversions
        val drawable = R.drawable.ab1_inversions
        alignYouBodyUI(modifier = Modifier.padding(all = 8.dp), name,drawable)
    }
}