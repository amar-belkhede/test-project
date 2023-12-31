package com.example.unit_test.api

import kotlinx.coroutines.delay
import javax.inject.Inject

// NetworkService interface
interface NetworkService {
    suspend fun performLoginRequest(username: String, password: String): Boolean
}

// NetworkServiceImpl implementation
class NetworkServiceImpl @Inject constructor() : NetworkService {
    override suspend fun performLoginRequest(username: String, password: String): Boolean {
        // Simulate network request
        delay(1000)
        // Return success for demo purposes
        return true
    }
}