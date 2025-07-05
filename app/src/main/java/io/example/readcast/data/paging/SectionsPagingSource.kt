package io.example.readcast.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.domain.model.toDomain
import io.example.readcast.data.remote.HomeApiService
import javax.inject.Inject

class SectionsPagingSource @Inject constructor(
    private val apiService: HomeApiService,
) : PagingSource<Int, Section<Card>>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Section<Card>> {
        val pageNo = params.key ?: 0
        return try {

            val response = apiService.getHome(page = pageNo)         // HomeResponse
            val mapped    = response.sections                 // List<SectionDto>
                .mapNotNull { it.toDomain() }                 // List<Section<Card>>
                .sortedBy { it.order }

            Log.d("Loading page no : ", pageNo.toString())

            LoadResult.Page(
                data = mapped,
                prevKey = if (pageNo == 0) null else pageNo - 1,
                nextKey = if (response.pagination?.nextPage != null) pageNo + 1 else null
            )
        } catch (e: Exception) {
            Log.e("exception --> :", e.stackTraceToString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Section<Card>>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}