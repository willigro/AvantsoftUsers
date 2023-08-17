package com.avantsoft.common.navigation

// TODO move to components if I create it
sealed class UserNavigation(val destination: String) {

    object Route: UserNavigation(destination = "user_route")
    object List : UserNavigation(destination = "user_list")
}