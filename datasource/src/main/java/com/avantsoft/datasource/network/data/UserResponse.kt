package com.avantsoft.datasource.network.data

data class UserResponse(
   val users: List<User>,
)

data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val email: String,
)