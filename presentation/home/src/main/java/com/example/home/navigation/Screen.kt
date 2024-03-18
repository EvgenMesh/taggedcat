package com.example.home.navigation

sealed class Screen(
    val route: String
) {
    data object Home : Screen("home")

    data object Cats : Screen(
        route = "menu/cats",
    )

    data object Pokemon : Screen(
        route = "menu/pokemons",
    )
}