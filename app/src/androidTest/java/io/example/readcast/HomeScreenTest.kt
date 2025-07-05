package io.example.readcast

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.example.readcast.data.enums.Layout
import io.example.readcast.ui.home.HomeScreen
import io.example.readcast.ui.main.MainViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Author: Anas Elshawaf
 * Created on: 05-7-2025
 */

@OptIn(ExperimentalPagingApi::class)
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun homeScreen_rendersAllSection_andMatchingLayouts() {

        val testFlow = MutableStateFlow(PagingData.from(dummySections()))

        val viewModel: MainViewModel = mockk(relaxed = true) {
            every { sections } returns testFlow
        }

        composeRule.setContent {
            HomeScreen(viewModel)
        }

        dummySections().forEach { section ->
            // 1 header text is visible
            composeRule
                .onNodeWithText(section.name, ignoreCase = true)
                .assertIsDisplayed()

            // 2 correct layout composable is in the tree
            val expectedTag = when (section.layout) {
                Layout.QUEUE, Layout.SQUARE   -> "SquareSectionTag"
                Layout.BIG_SQUARE             -> "BigSquareSectionTag"
                Layout.TWO_LINES_GRID         -> "GridSectionTag"
            }

            composeRule
                .onNodeWithTag(expectedTag, useUnmergedTree = true)
                .performScrollTo()
                .assertIsDisplayed()

        }
    }
}
