package io.example.readcast.domain.model

import com.google.gson.Gson
import io.example.readcast.data.dto.AudioArticleDto
import io.example.readcast.data.dto.AudioBookDto
import io.example.readcast.data.dto.EpisodeDto
import io.example.readcast.data.dto.PodcastDto
import io.example.readcast.data.dto.SectionDto
import io.example.readcast.data.enums.ContentType
import io.example.readcast.data.enums.Layout
import java.util.UUID

data class Section<T : Card>(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val layout: Layout,
    val contentType: ContentType,
    val order: Int,
    val cards: List<T>,
)


fun SectionDto.toDomain(): Section<Card>? {

    val layout = Layout.from(type) ?: return null
    val contentKind = ContentType.from(contentType) ?: return null

    val cards: List<Card> = when (contentKind) {
        ContentType.PODCAST -> content.map {
            Gson().fromJson(it, PodcastDto::class.java).toCard()
        }

        ContentType.EPISODE -> content.map {
            Gson().fromJson(it, EpisodeDto::class.java).toCard()
        }

        ContentType.AUDIO_BOOK -> content.map {
            Gson().fromJson(it, AudioBookDto::class.java).toCard()
        }

        ContentType.AUDIO_ARTICLE -> content.map {
            Gson().fromJson(it, AudioArticleDto::class.java).toCard()
        }
    }

    return Section(
        name = name,
        layout = layout,
        contentType = contentKind,
        order = order,
        cards = cards
    )
}
