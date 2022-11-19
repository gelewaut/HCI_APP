package com.example.fitlywebcompose.execution

import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.data.getViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun ExecuteScreen(
    id:Int,
    onNavigateToDetailScreen: (id:Int) -> Unit,
    viewModel: ExecuteViewModel = viewModel(factory = getViewModelFactory())
) {
    val uiState = viewModel.uiState
    if(uiState.currentRoutine == null || viewModel.uiState.currentRoutine!!.id != id) {
        viewModel.fetchRoutine(id)
    }
    val cou = 0
//    var exercise: CycleExercise? = uiState.cycleExercise
    val count = remember { mutableStateOf(0) }

    // create variable for current time
    var currentTime = remember {
        mutableStateOf(2000L)
    }
    // create variable for isTimerRunning
    var isTimerRunning = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = currentTime.value, key2 = isTimerRunning.value) {
        if(currentTime.value > 0 && isTimerRunning.value) {
            delay(1000L)
            currentTime.value -= 1000L
            count.value = (currentTime.value/1000).toInt()
            Log.v(null,"${currentTime.value}")
        }
    }


    if (currentTime.value <= 0) {
        if (viewModel.uiState.ok) {
            viewModel.execute()
            if (viewModel.uiState.cycleExercise!=null) {
                currentTime.value =
                    ((viewModel.uiState.cycleExercise!!.duration + 1) * 1000).toLong()
                isTimerRunning.value = true
            }
        } else {
            currentTime.value = 2000L
            onNavigateToDetailScreen(id)

        }
    }

    fun start() {
        if(viewModel.uiState.ok) {
            currentTime.value = ((viewModel.uiState.cycleExercise!!.duration+1)*1000).toLong()
            isTimerRunning.value = true
        }
    }

    Surface() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = uiState.currentRoutine?.name.toString(),
                            textAlign = TextAlign.Center
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { onNavigateToDetailScreen(id) },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "arrowBack",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    },
                    actions = {
                        ShowDetailsButton(
                            toggleDetails = {viewModel.toggleDetails(!uiState.showDetails)}
                        )
                    }
                )
            },
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Button(
                    onClick = {
                        viewModel.execute()
                        start() },
                    enabled = uiState.ok
                ) {
                    Text(text = stringResource(R.string.start))
                }
                LazyColumn() {
                    item {
                        viewModel.uiState.cycleExercise?.let {
                            /*
                            val timer = remember {
                                object : CountDownTimer(((viewModel.uiState.cycleExercise!!.duration+1) * 1000).toLong(), 1000) {
                                    override fun onTick(p0: Long) {
//                                        Log.v(null, "${(p0 / 1000).toInt()}")
                                        count.value = (p0 / 1000).toInt()
                                    }

                                    override fun onFinish() {
                                        Log.v(null, "Finished ${viewModel.uiState.cycleExercise!!.exercise.name}")
                                        viewModel.execute()
                                        this.cancel()
                                        if (viewModel.uiState.ok) {
//                                            Log.v(null, "${viewModel.uiState.ok}")
                                            this.start()
                                        } else {
                                            onNavigateToDetailScreen(1)
                                        }
                                    }
                                }.start()
                            }
                            * */
                            if (viewModel.uiState.showDetails) {
                                DetailedRoutineDisplay(
                                    viewModel.uiState.currentRoutine!!,
                                    viewModel.uiState.cycle!!,
                                    viewModel.uiState.cycleExercise!!,
                                    count.value,
                                    viewModel.uiState.cycleRepetitions,
                                    viewModel.uiState.repetitions_left
                                )
                            }
                            else {
                                RoutineDisplay(
                                    viewModel.uiState.currentRoutine!!,
                                    viewModel.uiState.cycle!!,
                                    viewModel.uiState.cycleExercise!!,
                                    count.value,
                                    viewModel.uiState.cycleRepetitions,
                                    viewModel.uiState.repetitions_left
                                )
                            }
                        }
                    }
                }
                viewModel.uiState.message?.let { Text(text = viewModel.uiState.message!!) }
            }
        }
    }
}


@Composable
fun ShowDetailsButton(toggleDetails: ()->Unit) {
    Button(onClick = toggleDetails) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(25.dp),
            backgroundColor = MaterialTheme.colors.secondary,

        ) {
            Box(
                contentAlignment = Center
            ) {
                Text(
                    text = stringResource(R.string.change_view),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
    }
}
