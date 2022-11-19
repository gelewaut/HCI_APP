package com.example.fitlywebcompose.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.rotationMatrix
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.data.model.Cycle
import com.example.fitlywebcompose.ui.theme.Shapes


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(
    cycle: Cycle
){
    var expandedState by remember{ mutableStateOf(false)}
    val rotationState by animateFloatAsState(
        targetValue= if(expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        backgroundColor = MaterialTheme.colors.secondary,
        onClick = {
            expandedState = !expandedState
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
               Text(
                   modifier = Modifier
                       .weight(3f),
                   text = cycle.name,
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Bold,
                   color = MaterialTheme.colors.onPrimary
               )
                Text(text = stringResource(id = R.string.repetitions) +" " + cycle.repetitions,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onPrimary)
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = { expandedState = !expandedState}) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDownCircle,
                            contentDescription = "dropDownArrow",
                            tint = MaterialTheme.colors.onPrimary
                            )
                }
            }
            if(expandedState){
                cycle.exercises.forEach{
                    cycleExercise ->
                    Text(text = "${cycleExercise.exercise.name}", fontWeight = FontWeight.Bold)
                    Row {
                        Text(text= stringResource(R.string.duration))
                        Text(text = " ${cycleExercise.duration} | ")
                        Text(text = stringResource(R.string.repetitions))
                        Text(text = " ${cycleExercise.repetitions} ")
                    }
                }

            }
        }
    }
}