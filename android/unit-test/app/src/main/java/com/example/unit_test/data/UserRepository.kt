package com.example.unit_test.data

import com.example.unit_test.model.UserProfile
import kotlinx.coroutines.delay
import javax.inject.Inject

interface UserRepository {
    suspend fun getUserProfile(userId: String): UserProfile
}

// UserRepositoryImpl implementation
class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun getUserProfile(userId: String): UserProfile {
        // Simulate fetching user profile data from a remote source
        delay(1000)
        return UserProfile(userId, "JohnDoe", "john.doe@example.com")
    }
}