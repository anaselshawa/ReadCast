package io.example.readcast.data.repository

import android.util.Log
import io.example.readcast.data.dto.toDomainSections
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.data.remote.SearchApiService
import io.example.readcast.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: SearchApiService,
) : SearchRepository {

    override suspend fun search(query: String): List<Section<Card>> {
        return try {
            apiService.search(query).toDomainSections()
        } catch (e: Exception) {
            Log.e("exception --> :", e.stackTraceToString())
            emptyList()
        }
    }
}
