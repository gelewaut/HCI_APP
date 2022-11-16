package com.example.fitlywebcompose.detail

import com.example.fitlywebcompose.ui.theme.Shapes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitlywebcompose.data.getViewModelFactory
import com.example.fitlywebcompose.detail.mvi.DetailViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitlywebcompose.R


@Composable
fun DetailScreen(
    id:Int,
    onNavigateToRoutineScreen: () -> Unit = {},
    onNavigateToExecuteScreen: (id:Int) -> Unit ={},
    viewModel: DetailViewModel = viewModel(
        factory = getViewModelFactory())
    ) {

        var uiState = viewModel.uiState
        if (uiState.routine == null){
            viewModel.getRoutine(id)
        }

        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = { onNavigateToRoutineScreen() },
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "arrowBack",
                    tint = MaterialTheme.colors.onPrimary
                )
                Text(text = stringResource(R.string.arrow_back)) 
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primaryVariant)
            ) {
                Text(
                    text = uiState.routine?.name.toString(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Text(
                text = stringResource(R.string.detail_description),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp)
            )

            Card(
                shape= Shapes.medium,
                backgroundColor = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                Row{
                    Text(text = stringResource(R.string.routine_detail), color = MaterialTheme.colors.onPrimary)
                    Text(text = uiState.routine?.detail.toString(),color = MaterialTheme.colors.onPrimary)
                }
                Row{
                    Text(text = stringResource(R.string.score),color = MaterialTheme.colors.onPrimary)
                    Text(text = uiState.routine?.score.toString(),color = MaterialTheme.colors.onPrimary)
                }
                Row{
                    Text(text = stringResource(R.string.difficulty),color = MaterialTheme.colors.onPrimary)
                    Text(text = uiState.routine?.difficulty.toString(),color = MaterialTheme.colors.onPrimary)
                }
                }
            }



            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                uiState.routine?.cycles?.forEach{ cycle->
                    item {
                        ExpandableCard(cycle)
                    }
                }
            }
        }


    }


