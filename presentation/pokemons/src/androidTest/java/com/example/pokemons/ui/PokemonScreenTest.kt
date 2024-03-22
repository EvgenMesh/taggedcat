package com.example.pokemons.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.entities.Pokemon
import com.example.presentation.ui.Content
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showPokemonList() {
        startScreen(listOf(
            Pokemon("test1"),
            Pokemon("test2"),
            Pokemon("test3"),
            Pokemon("test4"),
            Pokemon("test5"),
            Pokemon("test6"),
        ))
        composeTestRule.onNodeWithText("test1").assertIsDisplayed()
        composeTestRule.onNodeWithText("test2").assertIsDisplayed()
        composeTestRule.onNodeWithText("test3").assertIsDisplayed()
        composeTestRule.onNodeWithText("test4").assertIsDisplayed()
        composeTestRule.onNodeWithText("test5").assertIsDisplayed()
        composeTestRule.onNodeWithText("test6").assertIsDisplayed()
    }

    private fun startScreen(data: List<Pokemon>) {
        composeTestRule.setContent {
            Content(data)
        }
    }
}