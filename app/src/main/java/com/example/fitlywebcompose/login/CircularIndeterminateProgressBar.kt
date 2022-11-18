package com.example.fitlywebcompose.login

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
    if (isDisplayed){
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary
        )
    }
}