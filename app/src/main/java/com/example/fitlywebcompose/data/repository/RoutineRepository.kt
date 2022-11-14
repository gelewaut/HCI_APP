package com.example.fitlywebcompose.data.repository

import com.example.fitlywebcompose.data.model.*
import com.example.fitlywebcompose.data.network.RoutineRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutineRepository(
    private val remoteDataSource: RoutineRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val routineMutex = Mutex()
    // Cache of the latest routines got from the network.
    private var routines: List<Routine> = emptyList()


    suspend fun getRoutines(refresh: Boolean = false) : List<Routine> {
        if (refresh || routines.isEmpty()) {
            val result = remoteDataSource.getRoutines()
            //Thread-safe write
            routineMutex.withLock {
                this.routines = result.content.map { it.asModel() }
                this.routines.forEach { routine -> routine.cycles = getCycles(routine.id) }
            }
        }

        return routineMutex.withLock { this.routines }
    }

    suspend fun getRoutine(routineId: Int) : Routine? {
        return this.routines.find { routine -> routine.id == routineId }
    }

    private suspend fun getCycles(routineId: Int) : List<Cycle> {
        val result = remoteDataSource.getCycles(routineId)
        val toReturn = result.content.map { it.asModel() }
        toReturn.forEach { cycle -> cycle.exercises = getCycleExercises(cycle.id) }
        return toReturn
    }

    private suspend fun getCycleExercises(cycleId: Int) : List<CycleExercise> {
        val result = remoteDataSource.getCycleExercises(cycleId)
        return result.content.map { it.asModel() }
    }
}