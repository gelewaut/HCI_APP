package com.example.fitlywebcompose.routines

import com.example.fitlywebcompose.data.model.Routine

data class RoutineListUiState (
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val routines: List<Routine> ? = null,
    val message: String ? = null,
    val showRoutines: List<Routine>? = null
)