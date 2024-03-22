package com.example.main

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.main.model.CatUIModel
import com.example.main.ui.CatsContent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showCatsList() {
        startScreen(listOf(
            CatUIModel("test1", "test1", "test1", "test1"),
            CatUIModel("test2", "test2", "test2", "test2"),
            CatUIModel("test3", "test3", "test3", "test3"),
            CatUIModel("test4", "test4", "test4", "test4"),
        ))
        composeTestRule.onNodeWithText("test1").assertIsDisplayed()
        composeTestRule.onNodeWithText("test2").assertIsDisplayed()
        composeTestRule.onNodeWithText("test3").assertIsDisplayed()
        composeTestRule.onNodeWithText("test4").assertIsDisplayed()
    }

    private fun startScreen(data: List<CatUIModel>) {
        composeTestRule.setContent {
            CatsContent(data) {}
        }
    }
}