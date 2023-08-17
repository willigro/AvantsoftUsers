package com.avantsoft.datasource.network.api

import com.avantsoft.datasource.network.config.CONTENT_TYPE_JSON
import com.avantsoft.datasource.network.data.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApi {

    @Headers(CONTENT_TYPE_JSON)
    @GET("ce47ee53-6531-4821-a6f6-71a188eaaee0")
    suspend fun fetchUsers(): Response<UserResponse?>
}