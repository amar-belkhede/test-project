package com.example.unit_test.data

import com.example.unit_test.api.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UserRepositoryModule {

    @Provides
    fun provideUserRepository(networkService: NetworkService):UserRepository{
        return UserRepositoryImpl(networkService);
    }
}