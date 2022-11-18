package com.example.fitlywebcompose.execution

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitlywebcompose.data.model.Cycle
import com.example.fitlywebcompose.data.model.CycleExercise
import com.example.fitlywebcompose.data.model.Routine
import com.example.fitlywebcompose.R

@Composable
fun titleCard(Text: String) {
    Card() {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .background(color = MaterialTheme.colors.primary),
            contentAlignment = Alignment.Center,
        ) {
            androidx.compose.material.Text(
                text = Text,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DetailedRoutineDisplay(routine: Routine, cycle: Cycle, ex: CycleExercise, time: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 25.dp)
                .padding(start = 25.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(
                    text = stringResource(R.string.time_left),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 120.dp)
                ) {
                    Text(
                        text = "$time",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            titleCard(Text = stringResource(R.string.routine_name) + routine.name)
            Column(verticalArrangement = Arrangement.spacedBy(5.dp))
            {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                ) {
                    Text(
                        text = stringResource(R.string.routine_detail) + routine.detail,
                        fontSize = 17.sp
                    )
                }
                Text(
                    text = stringResource(R.string.routine_difficulty) + routine.difficulty,
                    fontSize = 17.sp
                )
                Text(
                    text = stringResource(R.string.routine_score) + routine.score,
                    fontSize = 17.sp
                )

            }
            titleCard(Text = stringResource(R.string.routine_cycle) + cycle.name)
            Column(verticalArrangement = Arrangement.spacedBy(5.dp))
            {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                ) {
                    Text(
                        text = stringResource(R.string.routine_detail) + cycle.detail,
                        fontSize = 17.sp
                    )
                }
                Text(
                    text = stringResource(R.string.type) + cycle.type,
                    fontSize = 17.sp
                )
                Text(
                    text = stringResource(R.string.repetitions) + cycle.repetitions,
                    fontSize = 17.sp
                )

            }
            titleCard(Text = stringResource(R.string.exercise) + ex.exercise.name)
            Column(verticalArrangement = Arrangement.spacedBy(5.dp))
            {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                ) {
                    Text(
                        text = stringResource(R.string.routine_detail) + ex.exercise.detail,
                        fontSize = 17.sp
                    )
                }
                Text(
                    text = stringResource(R.string.type) + ex.exercise.type,
                    fontSize = 17.sp
                )
                Text(
                    text = stringResource(R.string.repetitions) + ex.repetitions,
                    fontSize = 17.sp
                )
                Text(
                    text = stringResource(R.string.duration) + ex.duration,
                    fontSize = 17.sp
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp)
                        .padding(start = 40.dp),
                ) {
                }
            }
        }
    }
}