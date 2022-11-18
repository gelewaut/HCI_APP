package com.example.fitlywebcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.fitlywebcompose.data.getViewModelFactory
import com.example.fitlywebcompose.login.LoginScreen
import com.example.fitlywebcompose.routines.RoutineScreen
import com.example.fitlywebcompose.detail.DetailScreen
import com.example.fitlywebcompose.execution.ExecuteScreen
import com.example.fitlywebcompose.login.mvi.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitlywebcompose.detail.mvi.DetailViewModel
import com.example.fitlywebcompose.routines.RoutineScreenViewModel

@Composable
fun MyAppNavHost (
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.LoginScreen.route
) {
    val uri = "http://www.fitly.com"
    val secureUri = "https://www.fitly.com"
    val loginViewModel:LoginViewModel = viewModel(factory = getViewModelFactory())
    val routineScreenViewModel:RoutineScreenViewModel = viewModel(factory = getViewModelFactory())

    NavHost(
        navController = navController,
        startDestination = if(loginViewModel.uiState.isAuthenticated) "routines" else startDestination,
        modifier = modifier
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigateToRoutineScreen = {navController.navigate("routines")},
                loginViewModel
            )
        }
        composable("routines") {
            RoutineScreen(
                onNavigateToLoginScreen = {navController.navigate(startDestination)},
                onNavigateToDetailScreen = {id -> navController.navigate("routines/$id")},
                onNavigateToExecuteScreen = {id -> navController.navigate("routines/$id/execute")},
                loginViewModel = loginViewModel,
            )
        }
        composable(
            "routines/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/routines?id={id}" },
                navDeepLink { uriPattern = "$secureUri/routines?id={id}" })
        ) {
            DetailScreen(
                it.arguments!!.getInt("id"),
                onNavigateToRoutineScreen = {navController.navigate("routines")},
                onNavigateToExecuteScreen = {id -> navController.navigate("routines/$id/execute")},
                routineViewModel = routineScreenViewModel

            )
        }
        composable(
            "routines/{id}/execute",
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/routines?id={id}/execute" },
                navDeepLink { uriPattern = "$secureUri/routines?id={id}/execute" })
        ) {
            ExecuteScreen(it.arguments!!.getInt("id"),
                onNavigateToDetailScreen = {id -> navController.navigate("routines/$id")}
            )
        }
    }
}