package io.example.readcast.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import io.example.readcast.data.enums.Layout
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.ui.ErrorItem
import io.example.readcast.ui.ErrorScreen
import io.example.readcast.ui.LoadingItem
import io.example.readcast.ui.LoadingScreen
import io.example.readcast.ui.header.HomeHeaderLine
import io.example.readcast.ui.main.MainViewModel
import io.example.readcast.ui.header.SectionHeaderLine
import io.example.readcast.ui.sections.BigSquareSection
import io.example.readcast.ui.sections.GridSection
import io.example.readcast.ui.sections.SquareSection

@Composable
fun HomeScreen(viewModel: MainViewModel = hiltViewModel()) {

    val pagingSections = viewModel.sections.collectAsLazyPagingItems()
    val loadState = pagingSections.loadState


    when (// Initial loading state when first fetching data)
        loadState.refresh) {
        is androidx.paging.LoadState.Loading -> {
            LoadingScreen()
        }

        // Initial load error
        is androidx.paging.LoadState.Error -> {
            val error = loadState.refresh as androidx.paging.LoadState.Error
            ErrorScreen(
                message = error.error.localizedMessage ?: "Unknown error",
                onRetry = { pagingSections.retry() }
            )
        }

        // Otherwise show the content
        else -> {
            Column {

                Spacer(Modifier.height(10.dp))

                HomeHeaderLine()

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        count = pagingSections.itemCount,
                    ) { index ->
                        pagingSections[index]?.let { section ->
                            Spacer(Modifier.height(12.dp))
                            SectionHeaderLine(headerName = section.name)
                            SectionRenderer(section)
                        }
                    }

                    // Append loading indicator
                    if (loadState.append is androidx.paging.LoadState.Loading) {
                        item { LoadingItem() }
                    }

                    // Append error indicator
                    if (loadState.append is androidx.paging.LoadState.Error) {
                        item {
                            val error = loadState.append as androidx.paging.LoadState.Error
                            ErrorItem(
                                message = error.error.localizedMessage ?: "Unknown error",
                                onRetry = { pagingSections.retry() }
                            )
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun SectionRenderer(section: Section<Card>) {
    when (section.layout) {
        Layout.QUEUE, Layout.SQUARE -> SquareSection(section.cards)
        Layout.BIG_SQUARE -> BigSquareSection(section.cards)
        Layout.TWO_LINES_GRID -> GridSection(section.cards)
    }
}
