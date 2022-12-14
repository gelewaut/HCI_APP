package com.example.fitlywebcompose.routines

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitlywebcompose.R
import com.example.fitlywebcompose.data.getViewModelFactory
import com.example.fitlywebcompose.data.model.Routine
import com.example.fitlywebcompose.login.mvi.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoutineListItem(
    onNavigateToDetailScreen: (id:Int) -> Unit,
    onNavigateToExecuteScreen: (id:Int) -> Unit,
    viewModel: RoutineScreenViewModel ,
    routine : Routine
) {


    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 30.dp,
        onClick = { onNavigateToDetailScreen(routine.id) },
        modifier= Modifier.padding(6.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {


                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = routine.name,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(6f)
                        )
                        Box(
                            modifier = Modifier.weight(1f)) {
                            FavoriteButton(viewModel, routine.id, routine.isFavourite)
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = R.string.difficulty),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Left
                        )
                        Text(
                            text = ": ${routine.difficulty}",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Left
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(id = R.string.score),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Left
                        )
                        Text(
                            text = ": ${routine.score}",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Left
                        )
                    }

                        Button(
                            onClick = { onNavigateToExecuteScreen(routine.id) }
                        ) {
                            Text(
                                text = stringResource(R.string.execute),
                                textAlign = TextAlign.Center
                            )
                    }

        }
    }
}



@Composable
fun RoutineScreen(
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToDetailScreen: (id:Int) -> Unit,
    onNavigateToExecuteScreen: (id:Int) -> Unit,
    viewModel: RoutineScreenViewModel = viewModel(factory = getViewModelFactory()),
    loginViewModel: LoginViewModel
) {

    var showMenu by remember {
        mutableStateOf(false)
    }
    var showFilters by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue= if(showFilters) 180f else 0f
    )
    if(viewModel.uiState.showRoutines == null && !viewModel.uiState.isFetching) {
        viewModel.getRoutines()
    }
    Scaffold(
        topBar = {
            TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.app_name),
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = "fitness_center"
                    )
                }
            },

            actions={
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector=Icons.Default.AccountCircle,contentDescription = "accountCircle",Modifier.size(40.dp))
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = !showMenu }
                ) {
                    DropdownMenuItem(onClick = {
                        loginViewModel.logout()
                        onNavigateToLoginScreen()
                    }) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = "logout")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = stringResource(R.string.logout))
                    }
                }
            }
        ) }
    ) {

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                Button(onClick = {
                    showFilters = !showFilters
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                    Text(text = stringResource(R.string.filters))
                    Icon(
                        imageVector = Icons.Default.ArrowDropDownCircle,
                        contentDescription = "dropDownArrow",
                        tint = MaterialTheme.colors.onPrimary,
                        modifier= Modifier.rotate(rotationState)
                    )
                    DropdownMenu(expanded = showFilters, onDismissRequest = {showFilters = !showFilters}) {
                        DropdownMenuItem(onClick = {
                            viewModel.getFavoriteRoutines()
                            showFilters = !showFilters
                        }) {
                            Text(text = stringResource(R.string.favorites))
                        }
                        DropdownMenuItem(onClick = {
                            viewModel.getRoutinesByScore()
                            showFilters = !showFilters
                        }) {
                            Text(text = stringResource(R.string.score))
                        }
                        DropdownMenuItem(onClick = {
                            viewModel.getRoutinesByDifficulty("rookie")
                            showFilters = !showFilters
                        }) {
                            Text(text = stringResource(R.string.rookie))
                        }
                        DropdownMenuItem(onClick = {
                            viewModel.getRoutinesByDifficulty("beginner")
                            showFilters = !showFilters
                        }) {
                            Text(text = stringResource(R.string.beginner))
                        }
                        DropdownMenuItem(onClick = {
                            viewModel.getRoutinesByDifficulty("intermediate")
                            showFilters = !showFilters
                        }) {
                            Text(text = stringResource(R.string.intermediate))
                        }
                        DropdownMenuItem(onClick = {
                            viewModel.getRoutinesByDifficulty("advanced")
                            showFilters = !showFilters
                        }) {
                            Text(text = stringResource(R.string.advanced))
                        }
                        DropdownMenuItem(onClick = {
                            viewModel.getRoutinesByDifficulty("expert")
                            showFilters = !showFilters
                        }) {
                            Text(text = stringResource(R.string.expert))
                        }
                        DropdownMenuItem(onClick = {
                            viewModel.getRoutines()
                            showFilters = !showFilters
                        }) {
                            Text(text = stringResource(R.string.none))
                        }
                    }
                }
            }
            LazyVerticalGrid(
                columns=  GridCells.Adaptive(200.dp),
                content = {
                viewModel.uiState.showRoutines?.forEach {
                    item {
                        RoutineListItem(
                            onNavigateToDetailScreen =  onNavigateToDetailScreen,
                            onNavigateToExecuteScreen = onNavigateToExecuteScreen,
                            viewModel = viewModel,
                            routine = it,
                            )
                    }
                }
            }
            )
        }
    }

}