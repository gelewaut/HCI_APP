package com.example.fitlywebcompose.data

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import com.example.fitlywebcompose.MyApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application = (LocalContext.current.applicationContext as MyApplication)
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
    val routineRepository = application.routineRepository
    return ViewModelFactory(sessionManager, userRepository, routineRepository, LocalSavedStateRegistryOwner.current, defaultArgs)
}