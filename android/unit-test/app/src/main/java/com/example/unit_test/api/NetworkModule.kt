package com.example.unit_test.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent

// Dagger Hilt module
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideNetworkService(): NetworkService {
        return NetworkServiceImpl()
    }
}