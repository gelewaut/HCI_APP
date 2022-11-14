package com.example.fitlywebcompose.login.mvi

import com.example.fitlywebcompose.data.model.User

data class AuthViewState(
    val error: String? = null,
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null
)

val AuthViewState.canGetCurrentUser: Boolean get() = isAuthenticated