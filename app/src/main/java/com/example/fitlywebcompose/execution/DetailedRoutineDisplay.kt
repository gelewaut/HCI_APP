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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

@Preview(showBackground = true)
@Composable
fun DetailedRoutineDisplay(/*routine: Routine, cycle: Cycle, ex: CycleExercise*/) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 50.dp)
                .padding(start = 25.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            titleCard(Text = "Rutina: " /*+ routine.name*/)
            Column(verticalArrangement = Arrangement.spacedBy(5.dp))
            {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                ) {
                    Text(
                        text = "Detalle: "/* + routine.detail*/,
                        fontSize = 17.sp
                    )
                }
                Text(
                    text = "Dificultad: " /*+ routine.difficulty*/,
                    fontSize = 17.sp
                )
                Text(
                    text = "Puntuacion: "/* + routine.score*/,
                    fontSize = 17.sp
                )

            }
            titleCard(Text = "Ciclo: " /*+ cycle.name*/)
            Column(verticalArrangement = Arrangement.spacedBy(5.dp))
            {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                ) {
                    Text(
                        text = "Detalle: "/* + cycle.detail*/,
                        fontSize = 17.sp
                    )
                }
                Text(
                    text = "Tipo: " /*+ cycle.type*/,
                    fontSize = 17.sp
                )
                Text(
                    text = "Repeticiones: " /*+ cycle.repetitions*/,
                    fontSize = 17.sp
                )

            }
            titleCard(Text = "Ejercicio: " /*+ ex.exercise.name*/)
            Column(verticalArrangement = Arrangement.spacedBy(5.dp))
            {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                ) {
                    Text(
                        text = "Detalle: " /*+ ex.exercise.detail*/,
                        fontSize = 17.sp
                    )
                }
                Text(
                    text = "Tipo: " /*+ ex.exercise.type*/,
                    fontSize = 17.sp
                )
                Text(
                    text = "Repeticiones: " /*+ ex.repetitions*/,
                    fontSize = 17.sp
                )
                Text(
                    text = "Duracion: " /*+ ex.duration*/,
                    fontSize = 17.sp
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp)
                        .padding(start = 40.dp),
                ) {
                    Column {
                        Text(
                            text = "Tiempo Restante del ejercicio:",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 70.dp)
                        ) {
                            Text(
                                text = "00:45",
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}