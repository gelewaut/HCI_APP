package com.example.fitlywebcompose

import android.app.Application
import com.example.fitlywebcompose.data.SessionManager
import com.example.fitlywebcompose.data.network.RoutineRemoteDataSource
import com.example.fitlywebcompose.data.network.UserRemoteDataSource
import com.example.fitlywebcompose.data.network.api.RetrofitClient
import com.example.fitlywebcompose.data.repository.RoutineRepository
import com.example.fitlywebcompose.data.repository.UserRepository

class MyApplication : Application() {

    private val userRemoteDataSource : UserRemoteDataSource
        get() = UserRemoteDataSource(RetrofitClient.getApiUserService(this), sessionManager)

    private val routineRemoteDataSource : RoutineRemoteDataSource
        get() = RoutineRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    val sessionManager : SessionManager
        get() = SessionManager(this)

    val userRepository : UserRepository
        get() = UserRepository(userRemoteDataSource)

    val routineRepository : RoutineRepository
        get() = RoutineRepository(routineRemoteDataSource)
}