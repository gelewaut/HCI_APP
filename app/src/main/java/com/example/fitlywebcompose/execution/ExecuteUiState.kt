package com.example.fitlywebcompose.execution

import android.telecom.Call.Details
import com.example.fitlywebcompose.data.model.Cycle
import com.example.fitlywebcompose.data.model.CycleExercise
import com.example.fitlywebcompose.data.model.Routine
import com.example.fitlywebcompose.data.model.User

data class ExecuteUiState (
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentRoutine: Routine? = null,
    val message: String? = null,

    val ok:Boolean = false,
    val cycleIterator: Iterator<Cycle>? = null,
    val exerciseIterator: Iterator<CycleExercise>? = null,

    val timer: Int = 0,
    val cycle: Cycle? = null,
    val cycleExercise: CycleExercise? = null,
    val repetitions_left: Int = 0,
    val finished: Boolean = false,

    val showDetails: Boolean = false,
)

//val RoutineUiState.canGetCurrentUser: Boolean get() = isAuthenticated
//val RoutineUiState.canGetAllRoutines: Boolean get() = isAuthenticated
//val RoutineUiState.canGetCurrentRoutine: Boolean get() = isAuthenticated && currentRoutine != null