package com.example.fitlywebcompose.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.ui.theme.Typography

@Composable
fun DetailScreen(id:Int, onNavigateToExecuteScreen: (id:Int) -> Unit ) {
    Surface() {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text (
                text = "Routine $id"
            )
            Button(onClick = { onNavigateToExecuteScreen(id) }) {
                Text(
                    text = "Execute",
                    fontSize = Typography.body1.fontSize
                )
            }
        }
    }
}