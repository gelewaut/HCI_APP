package com.example.fitlywebcompose.detail.mvi

import com.example.fitlywebcompose.data.model.Routine
import com.example.fitlywebcompose.data.model.User


    data class DetailViewState(
        val isAuthenticated: Boolean = false,
        val isFetching: Boolean = false,
        val currentUser: User? = null,
        val message: String? = null,
        val routine: Routine? = null
    )
