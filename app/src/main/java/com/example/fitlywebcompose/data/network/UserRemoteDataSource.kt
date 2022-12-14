package com.example.fitlywebcompose.data.network

import com.example.fitlywebcompose.data.SessionManager
import com.example.fitlywebcompose.data.network.api.ApiUserService
import com.example.fitlywebcompose.data.network.model.NetworkCredentials
import com.example.fitlywebcompose.data.network.model.NetworkUser

class UserRemoteDataSource(
    private val apiUserService: ApiUserService,
    private val sessionManager: SessionManager
) : RemoteDataSource() {

    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            apiUserService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }

    suspend fun logout() {
        handleApiResponse { apiUserService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun getCurrentUser() : NetworkUser {
        return handleApiResponse { apiUserService.getCurrentUser() }
    }
}