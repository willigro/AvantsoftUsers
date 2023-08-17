package com.avantsoft.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.avantsoft.R
import com.avantsoft.components.navigation.UserNavigation
import com.avantsoft.components.theme.ListUsersAvantsoftTheme
import com.avantsoft.ui.users.usersGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    ListUsersAvantsoftTheme {
        Scaffold(
            topBar = {
                AppToolbar()
            }
        ) { innerPadding ->
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

@Composable
fun AppToolbar() {
    BoxWithConstraints(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStartPercent = 20, bottomEndPercent = 20))
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.size(width = maxWidth / 2, height = maxWidth / 2),
            painter = painterResource(R.drawable.logo),
            contentDescription = "",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AppToolbar() {
    ListUsersAvantsoftTheme {
        AppToolbar()
    }
}