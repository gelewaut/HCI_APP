package com.example.fitlywebcompose.routines

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlywebcompose.data.SessionManager
import com.example.fitlywebcompose.data.repository.RoutineRepository
import com.example.fitlywebcompose.data.repository.UserRepository
import kotlinx.coroutines.launch

class RoutineScreenViewModel(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val routineRepository: RoutineRepository
) : ViewModel() {
    var uiState by mutableStateOf(RoutineListUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set


    fun getRoutines() = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutines(false)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routines = response,
                showRoutines = response
            )
            Log.v(null, "Ok")
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
            Log.v(null, "not ok")
        }
    }

    fun addFavourite(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.addFavourite(routineId)
        }.onSuccess {
            getRoutines()
            uiState = uiState.copy(
                isFetching = false,
            )
            Log.v(null, "Ok Favourite")
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
            Log.v(null, "not ok Favourite")
        }
    }

    fun removeFavourite(routineId: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.removeFavourite(routineId)
        }.onSuccess {
            getRoutines()
            uiState = uiState.copy(
                isFetching = false,
            )
            Log.v(null, "Ok RemoveFavourite")
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
            Log.v(null, "not ok RemoveFavourite")
        }
    }

        fun getFavoriteRoutines(){
            uiState = uiState.copy(
                showRoutines = uiState.routines!!.filter { routine-> routine.isFavourite }
            )
    }

    fun getRoutinesByScore(){
        uiState = uiState.copy(
            showRoutines = uiState.routines!!.sortedByDescending { routine -> routine.score }
        )
    }

    fun getRoutinesByDifficulty(difficulty: String){
        uiState = uiState.copy(
            showRoutines = uiState.routines!!.filter { routine -> routine.difficulty.compareTo(difficulty) == 0 }
        )
    }
}