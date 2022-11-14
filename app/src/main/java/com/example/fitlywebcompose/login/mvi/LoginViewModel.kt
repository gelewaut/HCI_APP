package com.example.fitlywebcompose.login.mvi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitlywebcompose.data.SessionManager
import com.example.fitlywebcompose.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
            private val sessionManager: SessionManager,
            private val userRepository: UserRepository
        ) : ViewModel() {

    var uiState by mutableStateOf(AuthViewState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set

    private val _viewState = MutableStateFlow(AuthViewState())
    val viewState: StateFlow<AuthViewState>
        get() = _viewState

    fun doLogin(username: String, pass: String) = viewModelScope.launch{
        uiState = uiState.copy(
            isFetching = true,
            error = null
        )
        runCatching {
            userRepository.login(username, pass)
        }.onSuccess { response ->
            uiState = uiState.copy(
                isFetching = false,
                isAuthenticated = true
            )
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            uiState = uiState.copy(
                error = e.message,
                isFetching = false)
        }
    }
}