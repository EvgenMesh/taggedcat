package com.example.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.main.ui.CatsScreen
import com.example.menu.MenuScreen
import com.example.presentation.ui.PokemonScreen

@Composable
fun CatsNavigation() {
    val navController = rememberNavController()
    CatsNavHost(
        navController = navController
    )
}

@Composable
fun CatsNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            MenuScreen(
                onCatsClick = {
                    navController.navigate(
                        Screen.Cats.route
                    )
                },
                onPokemonClick = {
                    navController.navigate(
                        Screen.Pokemon.route
                    )
                }
            )
        }
        composable(
            route = Screen.Pokemon.route
        ) {
            PokemonScreen({
                navController.navigateUp()
            })
        }
        composable(
            route = Screen.Cats.route
        ) {
            CatsScreen({
                navController.navigateUp()
            })
        }
    }
}
