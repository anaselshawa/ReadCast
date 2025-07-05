package io.example.readcast

import io.example.readcast.data.enums.ContentType
import io.example.readcast.data.enums.Layout
import io.example.readcast.domain.model.Card
import io.example.readcast.domain.model.PodcastCard
import io.example.readcast.domain.model.Section

fun fakeSectionsPage(): List<Section<Card>> {

    val sec0Cards = listOf(
        PodcastCard(
            id = "pod",
            name = "The Kotlin Show",
            authorName = "JetBrains",
            description = "Deep dives into Kotlin features.",
            duration = "42:17",
            releaseDate = "2025-06-15",
            image = null,
            episodeCount = 128
        )
    )

    val sec1Cards = listOf(
        PodcastCard(
            id           = "pod_1",
            name         = "Android Bytes",
            authorName   = "Square",
            description  = "Short, focused Android tips.",
            duration     = "18:05",
            releaseDate  = "2025-05-20",
            image        = null,
            episodeCount = 54
        ),
        PodcastCard(
            id           = "pod_1_1",
            name         = "Compose Campfire",
            authorName   = "Google DevRel",
            description  = "Fireside chats on Jetpack Compose.",
            duration     = "35:42",
            releaseDate  = "2025-04-10",
            image        = null,
            episodeCount = 23
        )
    )

    val sec2Cards = listOf(
        PodcastCard(
            id           = "pod_2",
            name         = "Architecture Hour",
            authorName   = "Touchlab",
            description  = "Discussions on app architecture patterns.",
            duration     = "50:30",
            releaseDate  = "2025-03-05",
            image        = null,
            episodeCount = 75
        )
    )

    return listOf(
        Section(
            id = "sec_0",
            name = "Featured",
            layout = Layout.SQUARE,
            contentType = ContentType.PODCAST,
            order = 0,
            cards = sec0Cards
        ),
        Section(
            id          = "sec_1",
            name        = "Editor’s Picks",
            layout      = Layout.TWO_LINES_GRID,
            contentType = ContentType.PODCAST,
            order       = 1,
            cards       = sec1Cards
        ),
        Section(
            id          = "sec_2",
            name        = "New Releases",
            layout      = Layout.SQUARE,
            contentType = ContentType.PODCAST,
            order       = 2,
            cards       = sec2Cards
        )
    )
}
