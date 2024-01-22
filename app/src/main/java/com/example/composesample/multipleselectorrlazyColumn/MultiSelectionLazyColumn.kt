package com.example.composesample.multipleselectorrlazyColumn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MultiSelectLazyColumn() {
    var items by remember {
        mutableStateOf(
            (1..20).map {
                ListItem(
                    name = "Item $it",
                    isSelected = false
                )
            }
        )
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items.size) { i ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clickable {
                        items = items.mapIndexed { index, item ->
                            if (index == i) {
                                item.copy(
                                    name = item.name,
                                    isSelected = !item.isSelected
                                )
                            } else item
                        }
                    }
            ) {
                Text(text = items[i].name)
                if (items[i].isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.Green
                    )
                }
            }
        }
    }
}



