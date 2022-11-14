package com.example.fitlywebcompose.data.network.api

import com.example.fitlywebcompose.data.network.model.NetworkCycle
import com.example.fitlywebcompose.data.network.model.NetworkCycleExercise
import com.example.fitlywebcompose.data.network.model.NetworkPagedContent
import com.example.fitlywebcompose.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRoutineService {

    @GET("routines")
    suspend fun getRoutines() : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>

    @GET("routines/{routineId}/cycles")
    suspend fun getCycles(@Path("routineId") routineId: Int) : Response<NetworkPagedContent<NetworkCycle>>

    @GET("routines/{routineId}/cycles/{cycleId}")
    suspend fun getCycle(@Path("routineId") routineId: Int, @Path("cycleId") cycleId: Int) : Response<NetworkCycle>

    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(@Path("cycleId") cycleId: Int) : Response<NetworkPagedContent<NetworkCycleExercise>>

    @GET("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun getCycleExercise(@Path("cycleId") cycleId: Int, @Path("exerciseId") cycleExercise: Int) : Response<NetworkCycleExercise>
}