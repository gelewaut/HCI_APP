package com.example.fitlywebcompose.execution

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlywebcompose.data.SessionManager
import com.example.fitlywebcompose.data.model.*
import com.example.fitlywebcompose.data.repository.RoutineRepository
import kotlinx.coroutines.launch

class ExecuteViewModel(
    private val sessionManager: SessionManager,
    private val routineRepository: RoutineRepository
) : ViewModel() {

    var uiState by mutableStateOf(ExecuteUiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    fun fetchRoutine(id: Int) = viewModelScope.launch {
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        runCatching {
            routineRepository.getRoutine(id)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                currentRoutine = response
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

    fun prepareTimer () = viewModelScope.launch {
        uiState = uiState.copy(
            message = null
        )
        Log.v(null,"Hasta aca llega")
        if (getCycleIterator()) {
            getExerciseIterator()
            Log.v(null, "Llena todo ${uiState.ok}")
        } else {
            uiState = uiState.copy(
                message = "Could Start Timer",
                ok = false
            )
        }

    }

    private fun getCycleIterator() : Boolean {
        if (uiState.currentRoutine!!.cycles.isNotEmpty()) {
            uiState = uiState.copy(
                cycleIterator = uiState.currentRoutine!!.cycles.iterator()
            )
//            uiState.cycleIterator = uiState.currentRoutine!!.cycles.iterator()
            Log.v(null,"Hasta aca llega cycle iterator")
            return true
        }
        return false
    }

    private fun getExerciseIterator() {
        if (uiState.cycleIterator!!.hasNext()) {
            Log.v(null, "tiene next")
            val exe = uiState.cycleIterator!!.next()
            if(exe.exercises.isNotEmpty()) {
                Log.v(null,"no esta empty")
                uiState = uiState.copy(
                    ok = true,
                    exerciseIterator = exe.exercises.iterator(),
                    cycle = exe
                )
//                uiState.exerciseIterator = exe.exercises.iterator()
//                uiState.ok = true
            } else {
                getExerciseIterator()
            }
        } else {
            uiState = uiState.copy(
                ok = false
            )
        }
    }

    fun execute () = viewModelScope.launch {
        if (uiState.ok && uiState.exerciseIterator!!.hasNext()) {
            uiState = uiState.copy(
                cycleExercise = uiState.exerciseIterator!!.next()
            )
            return@launch
        } else {
            uiState = uiState.copy(
                ok = false
            )
        }
        while (!uiState.ok && uiState.cycleIterator!!.hasNext()) {
            getExerciseIterator()
        }
        if(uiState.ok && uiState.exerciseIterator!!.hasNext()) {
            uiState = uiState.copy(
                cycleExercise = uiState.exerciseIterator!!.next()
            )
            return@launch
        }

        uiState = uiState.copy(
            ok = false,
            cycleExercise = null,
            message = "Finished"
        )
        return@launch

        /*
        uiState = uiState.copy(
            isFetching = true,
            message = null
        )
        if(exerciseIterator!!.hasNext()) {
            uiState = uiState.copy(
                cycleExercise = exerciseIterator!!.next(),
                isFetching = false
            )
        } else {
            while (cycleIterator!!.hasNext() && uiState.isFetching) {
                uiState = uiState.copy(
                    cycle = cycleIterator!!.next()
                )
                exerciseIterator = uiState.cycle!!.exercises.iterator()
                if(exerciseIterator!!.hasNext()) {
                    uiState = uiState.copy(
                        cycleExercise = exerciseIterator!!.next(),
                        isFetching = false
                    )
                }
            }
        }
        if(uiState.isFetching) {
            uiState = uiState.copy(
                message = "Finished",
                isFetching = false,
                finished = true
            )
        }*/
    }

    fun updateTime(time: Int) {
        uiState = uiState.copy(
            timer = time
        )
    }
}