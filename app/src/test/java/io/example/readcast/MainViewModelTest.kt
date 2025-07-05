package io.example.readcast

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.domain.usecase.HomeSectionsUseCase
import io.example.readcast.ui.main.MainViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

/**
 * Author: Anas Elshawaf
 * Created on: 05-7-2025
 */

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    val testScheduler = TestCoroutineScheduler()
    private val dispatcher = StandardTestDispatcher(testScheduler)

    private val testScope = TestScope(dispatcher)

    // mocked dependency
    private val getSectionsUseCase: HomeSectionsUseCase = mock()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    //--------------Success path--------------------------
    @Test
    fun `sections emits paging data from use-case`() = testScope.runTest {
        // Arrange
        val expectedPage = fakeSectionsPage()
        whenever(getSectionsUseCase()).thenReturn(flowOf(PagingData.from(expectedPage)))
        viewModel = MainViewModel(getSectionsUseCase)

        // Act
        val pagingData = viewModel.sections.first()
        val differ = AsyncPagingDataDiffer(
            diffCallback = SectionDiffCallback,
            updateCallback = ListUpdateCallback,
            workerDispatcher = dispatcher
        )
        differ.submitData(pagingData)
        advanceUntilIdle()

        // Assert
        val actualSnapshot = differ.snapshot().items
        assertEquals(expectedPage, actualSnapshot)
        assertEquals("Featured", actualSnapshot[0].name)
        verify(getSectionsUseCase)
    }


    //--------------Error path--------------------------
    @Test
    fun `first page error surfaces as LoadState_Error`() = testScope.runTest {
        // Arrange
        val networkFailure = IOException("503 Service Unavailable")
        val errorFlow: Flow<PagingData<Section<Card>>> = Pager(
            PagingConfig(pageSize = 7)
        ) {
            object : PagingSource<Int, Section<Card>>() {
                override suspend fun load(params: LoadParams<Int>) =
                    LoadResult.Error<Int, Section<Card>>(networkFailure)

                override fun getRefreshKey(state: PagingState<Int, Section<Card>>) = null
            }
        }.flow

        whenever(getSectionsUseCase()).thenReturn(errorFlow)
        viewModel = MainViewModel(getSectionsUseCase)

        // Act
        val pagingData = viewModel.sections.first()
        val differ = AsyncPagingDataDiffer(
            diffCallback = SectionDiffCallback,
            updateCallback = ListUpdateCallback,
            workerDispatcher = dispatcher
        )
        differ.submitData(pagingData)
        advanceUntilIdle()

        // Assert
        val refresh = differ.loadStateFlow.first().refresh
        assertTrue(refresh is LoadState.Error && refresh.error === networkFailure)
        assertEquals(0, differ.itemCount)
        verify(getSectionsUseCase)
    }
}
