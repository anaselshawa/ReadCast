package io.example.readcast.domain.repository

import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section

interface SearchRepository {

   suspend fun search(query: String): List<Section<Card>>
}