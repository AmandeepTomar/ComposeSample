package com.example.composesample.basicslayouts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class SearchBarUI {

    @Composable
    fun searchBarUI(modifier: Modifier) {
        TextField(
            value = "", onValueChange = {},
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = "search icon")
            },
            placeholder = {
                Text(text = "search")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            modifier = modifier
                .fillMaxWidth()
                .heightIn(56.dp)
        )
    }

    @Preview
    @Composable
    fun searchBarUiPreview() {
        searchBarUI(Modifier.padding(all = 8.dp))
    }
}