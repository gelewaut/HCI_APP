package com.example.fitlywebcompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String) {
    object LoginScreen: Screen("Login", Icons.Filled.Home, "login_screen")
    object RoutineScreen: Screen("Routine", Icons.Filled.Home, "routine_screen")
    object DetailScreen: Screen("Detail", Icons.Filled.Home, "detail_screen")
    object ExecuteScreen: Screen("Execute", Icons.Filled.Home, "execute_screen")
}