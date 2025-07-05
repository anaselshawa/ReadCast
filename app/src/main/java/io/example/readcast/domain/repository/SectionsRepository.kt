package io.example.readcast.domain.repository

import androidx.paging.PagingData
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import kotlinx.coroutines.flow.Flow

interface SectionsRepository {
    fun getSections(): Flow<PagingData<Section<Card>>>
}