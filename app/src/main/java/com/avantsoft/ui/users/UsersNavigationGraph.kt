package com.avantsoft.ui.users

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.avantsoft.common.navigation.UserNavigation

fun NavGraphBuilder.usersGraph(navController: NavController) {
    navigation(
        route = UserNavigation.Route.destination,
        startDestination = UserNavigation.List.destination,
    ) {
        composable(UserNavigation.List.destination) {
            UsersScreenRoot(navController = navController)
        }
    }
}