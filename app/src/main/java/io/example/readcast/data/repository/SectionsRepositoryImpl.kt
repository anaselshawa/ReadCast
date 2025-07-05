package io.example.readcast.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.data.paging.SectionsPagingSource
import io.example.readcast.domain.repository.SectionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SectionsRepositoryImpl @Inject constructor(
    private val pagingSourceFactory: () -> SectionsPagingSource
) : SectionsRepository {

    override fun getSections(): Flow<PagingData<Section<Card>>> {
        return Pager(PagingConfig(pageSize = 10)) {
            pagingSourceFactory()
        }.flow
    }
}
