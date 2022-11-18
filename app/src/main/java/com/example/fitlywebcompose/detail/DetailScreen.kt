package com.example.fitlywebcompose.detail

import androidx.compose.animation.core.animateFloatAsState
import com.example.fitlywebcompose.ui.theme.Shapes
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
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
    viewModel: DetailViewModel = viewModel(factory = getViewModelFactory())
    )
    {

        var uiState = viewModel.uiState
        if (uiState.routine == null || uiState.routine!!.id != id){
            viewModel.getRoutine(id)
        }
        var showScoring by remember{ mutableStateOf(false) }
        val rotationState by animateFloatAsState(
            targetValue= if(showScoring) 180f else 0f
        )
        var selectedScore by remember{ mutableStateOf(0) }
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
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(it),
        ) {

            item {
                Text(
                    text = stringResource(R.string.detail_description),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(6.dp)
                )
            }
            item {
                Button(
                    onClick = { onNavigateToExecuteScreen(id) }
                ) {
                    Text(text = stringResource(R.string.execute))
                }
            }

            item {
                Card(
                    shape = Shapes.medium,
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                        .padding(6.dp)
                ) {
                    Column() {
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
                    }

                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Set routine score: ")
                        Text(text = " ${selectedScore}")
                        IconButton(
                            modifier = Modifier
                                .alpha(ContentAlpha.medium)
                                .rotate(rotationState),
                            onClick = { showScoring = !showScoring }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDownCircle,
                                contentDescription = "dropDownArrow",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                        DropdownMenu(
                            expanded = showScoring,
                            onDismissRequest = { showScoring = !showScoring }) {
                            DropdownMenuItem(onClick = {
                                selectedScore = 0
                                showScoring = !showScoring
                            }) {
                                Text(text = "0")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 1
                                showScoring = !showScoring
                            }) {
                                Text(text = "1")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 2
                                showScoring = !showScoring
                            }) {
                                Text(text = "2")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 3
                                showScoring = !showScoring
                            }) {
                                Text(text = "3")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 4
                                showScoring = !showScoring
                            }) {
                                Text(text = "4")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 5
                                showScoring = !showScoring
                            }) {
                                Text(text = "5")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 6
                                showScoring = !showScoring
                            }) {
                                Text(text = "6")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 7
                                showScoring = !showScoring
                            }) {
                                Text(text = "7")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 8
                                showScoring = !showScoring
                            }) {
                                Text(text = "8")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 9
                                showScoring = !showScoring
                            }) {
                                Text(text = "9")
                            }
                            DropdownMenuItem(onClick = {
                                selectedScore = 10
                                showScoring = !showScoring
                            }) {
                                Text(text = "10")
                            }
                        }
                        Button(onClick = { viewModel.addScore(id, selectedScore) }) {
                            Text(text = "SUBMIT")
                        }
                    }
                }
            }


            item {
                Text(
                    text = stringResource(R.string.cycles),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            item{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    uiState.routine?.cycles?.forEach { cycle ->

                            ExpandableCard(cycle)

                    }
                }
            }
        }
    }
        }





