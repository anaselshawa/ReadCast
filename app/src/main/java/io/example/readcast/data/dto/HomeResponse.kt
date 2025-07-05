package io.example.readcast.data.dto

import com.google.gson.annotations.SerializedName
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.Section
import io.example.readcast.domain.model.toDomain
import kotlinx.serialization.Serializable

@Serializable
data class PaginationDto(
    @SerializedName("next_page") val nextPage: String?,
    @SerializedName("total_pages") val totalPages: Int,
)

@Serializable
data class HomeResponse(
    val sections: List<SectionDto>,
    val pagination: PaginationDto? = null,
)


fun HomeResponse.toDomainSections(): List<Section<Card>> = sections
    .mapNotNull { it.toDomain() }
    .sortedBy { it.order }
