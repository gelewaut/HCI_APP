package com.example.fitlywebcompose.execution

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.fitlywebcompose.data.getViewModelFactory
import com.example.fitlywebcompose.routines.RoutineScreenViewModel
import com.example.fitlywebcompose.ui.theme.Typography
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitlywebcompose.data.model.Cycle
import com.example.fitlywebcompose.data.model.CycleExercise
import org.intellij.lang.annotations.JdkConstants
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.absoluteValue

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
//                Button(
//                    onClick = { viewModel.prepareTimer() },
//                    enabled = uiState.currentRoutine!=null
//                ) {
//                    Text(text = "Prepare Timer")
//                }
                Button(
                    onClick = { viewModel.execute() },
                    enabled = uiState.ok
                ) {
                    Text(text = "Start")
                }
//            exercise?.let { ShowExercise(cycleExercise = it) }
                LazyColumn() {
                    item {
                        uiState.cycleExercise?.let {
                            val timer = remember {
                                object : CountDownTimer(((uiState.cycleExercise.duration+1) * 1000).toLong(), 1000) {
                                    override fun onTick(p0: Long) {
                                        Log.v(null, "${(p0 / 1000).toInt()}")
                                        count.value = (p0 / 1000).toInt()
                                    }

                                    override fun onFinish() {
                                        viewModel.execute()
                                        Log.v(null, "Finished ${uiState.cycleExercise.exercise.name}")
                                        this.cancel()
                                        if (viewModel.uiState.ok) {
                                            Log.v(null, "${viewModel.uiState.ok}")
                                            this.start()
                                        } else {
                                            onNavigateToDetailScreen(1)
                                        }
                                    }
                                }.start()
                            }
                            if (uiState.showDetails) {
                                DetailedRoutineDisplay(
                                    uiState.currentRoutine!!,
                                    uiState.cycle!!,
                                    uiState.cycleExercise!!,
                                    count.value
                                )
                            }
                            else {
                                RoutineDisplay(
                                    uiState.currentRoutine!!,
                                    uiState.cycle!!,
                                    uiState.cycleExercise!!,
                                    count.value
                                )
                            }
                        }
                    }
                }
                uiState.message?.let { Text(text = uiState.message) }
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

/*
@Composable
fun ShowText(count:Int) {
    Text(text = "$count")
}

@Composable
fun ShowExercise (
    cycleExercise: CycleExercise
) {
    Text(text = cycleExercise.exercise.name?: "")
    Text(text = cycleExercise.duration.toString())
}

@Composable
fun ExecuteExercise (
    exercise: CycleExercise,
    nextExercise: ()-> Unit,
    tick: (Int) -> Unit
) {
    object : CountDownTimer((exercise.duration * 1000).toLong(), 1000) {
        override fun onTick(p0: Long) {
            Log.v(null,"${(p0/1000).toInt()}")
            tick((p0/1000).toInt())
        }

        override fun onFinish() {
            nextExercise()
            Log.v(null, "Finished ${exercise.exercise.name}")
            this.cancel()
        }
    }.start()
}
* */
