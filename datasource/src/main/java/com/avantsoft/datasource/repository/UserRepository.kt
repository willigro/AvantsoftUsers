package com.avantsoft.datasource.repository

import com.avantsoft.datasource.network.api.UserApi
import com.avantsoft.datasource.network.data.UserResponse
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface UserRepository {
    suspend fun fetchUsers(): UserResponse?
}

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
) : UserRepository {
    override suspend fun fetchUsers(): UserResponse? = withContext(Dispatchers.IO) {
        userApi.fetchUsers().body()
    }
}