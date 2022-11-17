package com.example.fitlywebcompose.data.network

import com.example.fitlywebcompose.data.network.api.ApiRoutineService
import com.example.fitlywebcompose.data.network.model.NetworkCycle
import com.example.fitlywebcompose.data.network.model.NetworkCycleExercise
import com.example.fitlywebcompose.data.network.model.NetworkPagedContent
import com.example.fitlywebcompose.data.network.model.NetworkRoutine

class RoutineRemoteDataSource(
    private val apiRoutineService: ApiRoutineService
) : RemoteDataSource() {

    suspend fun getRoutines() : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines()
        }
    }

    suspend fun getRoutine(routineId: Int) : NetworkRoutine {
        return handleApiResponse {
            apiRoutineService.getRoutine(routineId)
        }
    }

    suspend fun getCycles(routineId: Int) : NetworkPagedContent<NetworkCycle> {
        return handleApiResponse {
            apiRoutineService.getCycles(routineId)
        }
    }

    suspend fun getCycle(routineId: Int, cycleId: Int) : NetworkCycle {
        return handleApiResponse {
            apiRoutineService.getCycle(routineId, cycleId)
        }
    }

    suspend fun getCycleExercises(cycleId: Int) : NetworkPagedContent<NetworkCycleExercise> {
        return handleApiResponse {
            apiRoutineService.getCycleExercises(cycleId)
        }
    }

    suspend fun getCycleExercise(cycleId: Int, exerciseId: Int) : NetworkCycleExercise {
        return handleApiResponse {
            apiRoutineService.getCycleExercise(cycleId, exerciseId)
        }
    }

    suspend fun getFavourites() : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getFavourites()
        }
    }

    suspend fun addFavourite(routineId: Int) {
        return handleApiResponse {
            apiRoutineService.addFavourite(routineId)
        }
    }

    suspend fun removeFavourite(routineId: Int) {
        return handleApiResponse {
            apiRoutineService.removeFavourite(routineId)
        }
    }

    suspend fun addScore(routineId: Int, score: Int, review: String) {
        return handleApiResponse {
            apiRoutineService.addScore(routineId, score, review)
        }
    }
}