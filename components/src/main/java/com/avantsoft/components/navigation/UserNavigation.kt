package com.avantsoft.components.navigation

sealed class UserNavigation(val destination: String) {

    object Route: UserNavigation(destination = "user_route")
    object List : UserNavigation(destination = "user_list")
}