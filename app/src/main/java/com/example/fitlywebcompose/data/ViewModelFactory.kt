package com.example.fitlywebcompose.data

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.fitlywebcompose.data.repository.RoutineRepository
import com.example.fitlywebcompose.data.repository.UserRepository
import com.example.fitlywebcompose.detail.mvi.DetailViewModel
import com.example.fitlywebcompose.execution.ExecuteViewModel
import com.example.fitlywebcompose.login.mvi.LoginViewModel
import com.example.fitlywebcompose.routines.RoutineScreenViewModel

class ViewModelFactory constructor(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val routineRepository: RoutineRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(sessionManager, userRepository)
            isAssignableFrom(DetailViewModel::class.java)->
                DetailViewModel(sessionManager, routineRepository)
            isAssignableFrom(RoutineScreenViewModel::class.java) ->
                RoutineScreenViewModel(sessionManager, userRepository, routineRepository)
            isAssignableFrom(ExecuteViewModel::class.java) ->
                ExecuteViewModel(sessionManager, routineRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}