package com.example.fitlywebcompose.routines

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.ui.theme.Typography
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun RoutineListItem() {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = Color.Blue)
            .padding(20.dp),
        contentAlignment = Alignment.CenterStart,

        ) {
        Row() {

            Box(
                modifier = Modifier
                    .height(100.dp)
                    .width(150.dp)
                    .background(color = Color.Gray)
            ) {}
            Column (){

                Box(
                    modifier = Modifier
                        .width(150.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "Rutina 1",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {},
                    ) {
                        Text(
                            text = "Comenzar\nRutina",
                            textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoutineList() {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)

    ) {
        items(10) {
            RoutineListItem()
        }
    }
}

@Composable
fun RoutineScreen(
    onNavigateToDetailScreen: (id:Int) -> Unit
) {
    Surface() {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = { onNavigateToDetailScreen(1) }) {
                Text(
                    text = "Go To Routine",
                    fontSize = Typography.body1.fontSize
                )
            }
        }
    }
}