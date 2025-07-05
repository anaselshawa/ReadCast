package io.example.readcast.domain.usecase

import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    suspend operator fun invoke(query: String): List<Section<Card>> = searchRepository.search(query)

}