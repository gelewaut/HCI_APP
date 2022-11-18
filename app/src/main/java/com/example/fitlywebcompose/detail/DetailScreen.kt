package com.example.fitlywebcompose.detail

import com.example.fitlywebcompose.ui.theme.Shapes
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitlywebcompose.data.getViewModelFactory
import com.example.fitlywebcompose.routines.RoutineScreenViewModel
import com.example.fitlywebcompose.detail.mvi.DetailViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.routines.FavoriteButton


@Composable
fun DetailScreen(
    id:Int,
    onNavigateToRoutineScreen: () -> Unit = {},
    onNavigateToExecuteScreen: (id:Int) -> Unit ={},
    viewModel: DetailViewModel = viewModel(factory = getViewModelFactory()),
    routineViewModel: RoutineScreenViewModel = viewModel(factory = getViewModelFactory())
    )
    {

        var uiState = viewModel.uiState
        if (uiState.routine == null || uiState.routine!!.id != id){
            viewModel.getRoutine(id)
        }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text=uiState.routine?.name.toString(), textAlign = TextAlign.Center)},
                navigationIcon = {
                    IconButton(
                        onClick = { onNavigateToRoutineScreen() },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "arrowBack",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                },
                actions = {
//                    uiState.routine?.isFavourite?.let { FavoriteButton(routineViewModel, id, it) }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Share, contentDescription = "share")
                    }
                }

            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(it),
        ) {


            Text(
                text = stringResource(R.string.detail_description),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp)
            )


            Card(
                shape = Shapes.medium,
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier
                    .padding(6.dp)
            ) {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Row {
                        Text(
                            text = stringResource(R.string.routine_detail),
                            color = MaterialTheme.colors.onPrimary
                        )
                        Text(
                            text = uiState.routine?.detail.toString(),
                            color = MaterialTheme.colors.onPrimary
                        )

                    }
                    Row {
                        Text(
                            text = "${stringResource(R.string.score)}:",
                            color = MaterialTheme.colors.onPrimary
                        )
                        Text(
                            text = uiState.routine?.score.toString(),
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                    Row {
                        Text(
                            text = "${stringResource(R.string.difficulty)}:",
                            color = MaterialTheme.colors.onPrimary
                        )
                        Text(
                            text = uiState.routine?.difficulty.toString(),
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                    Button(
                        onClick = { onNavigateToExecuteScreen(id) }
                    ) {
                        Text(text = stringResource(R.string.execute))
                    }
                }

            }


            Text(text = stringResource(R.string.cycles),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

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

    }


