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
            prepareTimer()
        }.onFailure { e ->
            uiState = uiState.copy(
                message = e.message,
                isFetching = false
            )
            Log.v(null, "not ok")
        }
    }

    private fun prepareTimer () = viewModelScope.launch {
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
        Log.v(null, "${uiState.cycleRepetitions}")
        if (uiState.cycleRepetitions > 0) {
            val list = uiState.cycle!!.exercises
            uiState = uiState.copy(
                exerciseIterator = list.iterator(),
                cycleRepetitions = uiState.cycleRepetitions-1,
                ok = true,
            )
            Log.v(null, "Entraaaaaaaaaaaaaaaaaaa")
        } else if (uiState.cycleIterator!!.hasNext()) {
            val exe = uiState.cycleIterator!!.next()
                if(exe.exercises.isNotEmpty()) {
                    uiState = uiState.copy(
                        ok = true,
                        cycle = exe,
                        exerciseIterator = exe.exercises.iterator(),
                        cycleRepetitions = exe.repetitions -1
                    )
                } else {
                    getExerciseIterator()
                }
        } else {
            uiState = uiState.copy(
                ok = false
            )
        }
    }

    fun toggleDetails(showDetails: Boolean) {
        uiState = uiState.copy(
            showDetails = showDetails
        )
    }

    fun execute () = viewModelScope.launch {
        Log.v(null, "Entrooooooo ${uiState.cycleRepetitions}")
        if (uiState.repetitions_left > 0) {
            uiState = uiState.copy(
                repetitions_left = uiState.repetitions_left-1
            )
            return@launch
        }

        if (uiState.ok && uiState.exerciseIterator!!.hasNext()) {
            val exe = uiState.exerciseIterator!!.next()
            uiState = uiState.copy(
                cycleExercise = exe,
                repetitions_left = exe.repetitions
            )
            return@launch
        } else {
            uiState = uiState.copy(
                ok = false
            )
        }
        while (!uiState.ok && (uiState.cycleIterator!!.hasNext() || uiState.cycleRepetitions > 0)) {
//            Log.v(null, "Repetitions left: ${uiState.cycleRepetitions}")
            getExerciseIterator()
//            Log.v(null,"Repetitions left: ${uiState.cycleRepetitions} ok = ${uiState.ok}")
        }
        Log.v(null, "${uiState.cycleRepetitions}     ${uiState.exerciseIterator!!.hasNext()}")

        if(uiState.ok && uiState.exerciseIterator!!.hasNext()) {
            val exe = uiState.exerciseIterator!!.next()
            uiState = uiState.copy(
                cycleExercise = exe,
                repetitions_left = exe.repetitions
            )
            return@launch
        }

        uiState = uiState.copy(
            ok = false,
            cycleExercise = null,
            message = "Finished"
        )
        Log.v(null,"Corte ${uiState.ok}")

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