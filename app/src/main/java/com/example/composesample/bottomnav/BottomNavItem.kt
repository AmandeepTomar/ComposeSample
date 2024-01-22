package com.example.composesample.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
) {

    companion object{
        fun getItems() = listOf(
            BottomNavItem(name = "home", "home", Icons.Default.Home),
            BottomNavItem(name = "home", "chat", Icons.Default.Notifications, badgeCount = 100),
            BottomNavItem(name = "home", "settings", Icons.Default.Settings)
        )
    }

}
