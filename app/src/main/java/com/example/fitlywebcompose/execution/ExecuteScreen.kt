package com.example.fitlywebcompose.execution

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.ui.theme.Typography
import org.intellij.lang.annotations.JdkConstants
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun textCard(Text: String) {
    Card() {
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(100.dp)
                .background(color = MaterialTheme.colors.primary),
            contentAlignment = Center,
        ) {
            Text(
                text = Text,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoutineDisplay() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .align(TopCenter)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            textCard(Text = "EJECUTANDO: NOMBRE DE RUTINA")
            Text(
                text = "TIEMPO RESTANTE DE EJERCICIO",
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "02:30",
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            textCard(Text = "ciclo 1")
            textCard(Text = "Nombre ej")

        }

    }
}

@Composable
fun ExecuteScreen(id:Int, onNavigateToDetailScreen: (id:Int) -> Unit ) {
    Surface() {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Executing Routine $id")
            Button(onClick = { onNavigateToDetailScreen(id) }) {
                Text(
                    text = "Go Back",
                    fontSize = Typography.body1.fontSize
                )
            }
        }
    }
}

/*

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun ExecuteScreen(id:Int, onNavigateToDetailScreen: (id:Int) -> Unit) {
    var count by remember { mutableStateOf(10) }
    var time = Timer(count = count, onCountChange = {count = it})
    Surface() {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text (text = "$count")
            Button(onClick = { time }) {
                Text(text = "Start")
            }
            Text(text = "Executing Routine $id")
            Button(onClick = { onNavigateToDetailScreen(id) }) {
                Text(
                    text = "Go Back",
                    fontSize = Typography.body1.fontSize,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}
@Composable
fun Timer (count:Int, onCountChange: (Int) -> Unit) {
    val time = object : CountDownTimer((count * 1000).toLong(), 1000) {
        override fun onTick(p0: Long) {
            onCountChange ((p0/1000).toInt())
        }

        override fun onFinish() {
            onCountChange (10)
            this.cancel()
        }

    }.start()
}



@Preview (showBackground = true)
@Composable
fun ShowScreen() {
    ExecuteScreen(id = 1, onNavigateToDetailScreen = {})
}
*/
