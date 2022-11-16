package com.example.fitlywebcompose.routines

import android.content.ClipData
import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitlywebcompose.data.getViewModelFactory
import com.example.fitlywebcompose.data.model.Routine

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {

    var isFavorite by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
        }
    ) {
        Icon(
            tint = color,

            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}

@Composable
fun RoutineListItem(
    routine : Routine,
    //onClick: () -> Unit,
    //modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp,
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
                .padding(20.dp),
            contentAlignment = Alignment.CenterStart,

            ) {

            Row {
                Card(
                    //modifier = modifier.clickable { onClick() },
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .width(150.dp)
                            .background(color = Color.Gray)
                    ) {/*foto del ejercicio*/ }
                }
                Column() {

                    Box(
                        modifier = Modifier
                            .width(150.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = routine.name,
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
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                FavoriteButton()
            }
        }
    }
}

@Composable
fun RoutineScreen(
    onNavigateToDetailScreen: (id:Int) -> Unit,
    viewModel: RoutineScreenViewModel = viewModel(factory = getViewModelFactory())
) {
    if(viewModel.uiState.routines == null) {
        viewModel.getRoutines()
    }
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
            LazyColumn() {
                viewModel.uiState.routines?.forEach {
                    item { RoutineListItem(routine = it) }
                }
            }
        }

    }
}