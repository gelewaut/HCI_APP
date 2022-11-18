package com.example.fitlywebcompose.detail.mvi

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlywebcompose.data.SessionManager
import com.example.fitlywebcompose.data.repository.RoutineRepository
import com.example.fitlywebcompose.data.repository.UserRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val sessionManager: SessionManager,
                          private val routineRepository: RoutineRepository
) : ViewModel() {

    var uiState by mutableStateOf(DetailViewState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    private val _detailViewState = MutableStateFlow(DetailViewState())
    val viewState: StateFlow<DetailViewState>
        get() = _detailViewState

    fun getRoutine(id: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutine(id)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                routine = response
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                message = e.message,
                isFetching = false)
        }
    }

    fun addScore(routineId: Int, score:Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.addScore(routineId, score, "")
        }.onSuccess {
            uiState = uiState.copy(
                isFetching = false,
            )
            getRoutine(id = uiState.routine!!.id)
            Log.v(null, "Ok Score")
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
            Log.v(null, "Not Ok Score ${e.message}")
        }
    }
}