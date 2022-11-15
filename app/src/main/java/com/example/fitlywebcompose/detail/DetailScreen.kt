package com.example.fitlywebcompose.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.ui.theme.Typography

@Composable
fun DetailScreen(
    id:Int,
    onNavigateToExecuteScreen: (id:Int) -> Unit ={}) {
        Column(
            verticalArrangement = Arrangement.Top
        ){
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(4.dp)
                ) {
                Icon(imageVector= Icons.Default.ArrowBack,
                    contentDescription ="arrowBack",
                    tint = MaterialTheme.colors.onPrimary
                )
                Text(text = "Back")
            }

            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primaryVariant)
                    ){
                Text(
                    text = "Routine $id",
                    fontSize = 25.sp,
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colors.onPrimary
                    )
            }
            Text(
                text = "Here you can see the cycles, exercises and reps of this routine, you can press de arrow on the specific cycle to see more info.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp)
                )
        }

    LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
    ){
        items(3){
            ExpandableCard("Cycle1", "Exercisessssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
        }
    }
}


