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
import com.example.fitlywebcompose.data.model.Cycle
import com.example.fitlywebcompose.data.model.CycleExercise
import com.example.fitlywebcompose.data.model.Routine

@Composable
fun RoutineDisplay(routine: Routine, cycle: Cycle, ex: CycleExercise, time: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            textCard(Text = "EJECUTANDO: ${routine.name}")
            Text(
                text = "TIEMPO RESTANTE DE EJERCICIO",
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

            textCard(Text = cycle.name)
            textCard(Text = "${ex.exercise.name}")

        }

    }
}

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
