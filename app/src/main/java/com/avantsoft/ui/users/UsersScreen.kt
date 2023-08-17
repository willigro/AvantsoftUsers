package com.avantsoft.ui.users

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun UsersScreenRoot(
    navController: NavController,
    viewModel: UsersScreenViewModel = hiltViewModel(),
) {
    viewModel.fetchUsers()

    UsersScreen(navController = navController)
}

@Composable
fun UsersScreen(
    navController: NavController,
) {

}