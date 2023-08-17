package com.avantsoft.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.avantsoft.components.navigation.UserNavigation
import com.avantsoft.components.theme.ListUsersAvantsoftTheme
import com.avantsoft.ui.users.usersGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    ListUsersAvantsoftTheme {
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationGraph()
            }
        }
    }
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = UserNavigation.Route.destination,
    ) {
        usersGraph(navController = navController)
    }
}