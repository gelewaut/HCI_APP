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
            val favourites = remoteDataSource.getFavourites().content.map { it.asModel() }
            val toRet = result.content.map {it.asModel()}
            favourites.forEach { favourite ->
                var index = toRet.find { it -> it.id == favourite.id }
                index?.isFavourite = true
            }
//            toRet.forEach { routine -> routine.cycles = getCycles(routine.id) }
            //Thread-safe write
            routineMutex.withLock {
                this.routines = toRet
//                this.routines.forEach { routine -> routine.cycles = getCycles(routine.id) }
            }
        }

        return routineMutex.withLock { this.routines }
    }

    suspend fun getRoutine(routineId: Int) : Routine? {
//        var result = remoteDataSource.getRoutine(routineId).asModel()
        var result = routines.find {routine -> routine.id == routineId }
        result?.cycles = getCycles(routineId)
        return result
//        return this.routines.find { routine -> routine.id == routineId }
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

    suspend fun addFavourite(routineId: Int) {
        remoteDataSource.addFavourite(routineId)
        getRoutines(true)
    }

    suspend fun removeFavourite(routineId: Int) {
        remoteDataSource.removeFavourite(routineId)
        getRoutines(true)
    }

    suspend fun addScore(routineId: Int, score:Int, review: String) {
        remoteDataSource.addScore(routineId, score, review)
        getRoutines(true)
    }
}