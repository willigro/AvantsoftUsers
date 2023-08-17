package com.avantsoft.datasource.repository.di

import com.avantsoft.datasource.network.api.UserApi
import com.avantsoft.datasource.repository.UserRepository
import com.avantsoft.datasource.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(
        userApi: UserApi,
    ): UserRepository = UserRepositoryImpl(
        userApi,
    )
}