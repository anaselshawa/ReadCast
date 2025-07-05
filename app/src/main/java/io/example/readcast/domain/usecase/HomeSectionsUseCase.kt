package io.example.readcast.domain.usecase

import androidx.paging.PagingData
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.domain.repository.SectionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeSectionsUseCase @Inject constructor(private val sectionsRepository: SectionsRepository) {

    operator fun invoke(): Flow<PagingData<Section<Card>>> = sectionsRepository.getSections()

}