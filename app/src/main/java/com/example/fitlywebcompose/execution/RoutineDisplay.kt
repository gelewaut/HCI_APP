package com.example.fitlywebcompose.execution

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.res.stringResource
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.data.model.Cycle
import com.example.fitlywebcompose.data.model.CycleExercise
import com.example.fitlywebcompose.data.model.Routine

@Composable
fun RoutineDisplay(
    routine: Routine,
    cycle: Cycle,
    ex: CycleExercise,
    time: Int,
    cycleRepetitions: Int,
    exerciseRepetitions: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 25.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            textCard(Text = stringResource(R.string.executing) + routine.name)
            Text(
                text = stringResource(R.string.time_left),
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "$time",
                textAlign = TextAlign.Center,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )

            textCard(Text = stringResource(R.string.cycles) + cycle.name + "  " + stringResource(R.string.repetitions_left) + cycleRepetitions)
            textCard(Text = ex.exercise.name + "  " + stringResource(R.string.repetitions_left) + exerciseRepetitions)
        }

    }
}

@Composable
fun textCard(Text: String) {
    Card() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
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
