package io.example.readcast.domain.model

import io.example.readcast.data.dto.AudioArticleDto
import io.example.readcast.data.dto.AudioBookDto
import io.example.readcast.data.dto.EpisodeDto
import io.example.readcast.data.dto.PodcastDto
import io.example.readcast.utils.formatDurationToHM
import io.example.readcast.utils.getTimeAgo

sealed interface Card {
    val id: String
    val episodeCount: Int
    val name: String
    val authorName: String?
    val description: String?
    val image: String?
    val duration: String
    val releaseDate: String?
}

data class PodcastCard(
    override val name: String,
    override val authorName: String?,
    override val description: String?,
    override val duration: String,
    override val releaseDate: String? = null,
    override val id: String,
    override val image: String?,
    override val episodeCount: Int,
) : Card

data class EpisodeCard(
    override val id: String,
    override val image: String?,
    override val name: String,
    override val authorName: String?,
    override val description: String?,
    override val duration: String,
    override val releaseDate: String?,
    override val episodeCount: Int,
) : Card

data class AudioBookCard(
    override val id: String,
    override val image: String?,
    override val name: String,
    override val authorName: String?,
    override val description: String?,
    override val duration: String,
    override val releaseDate: String?,
    override val episodeCount: Int,
) : Card

data class ArticleCard(
    override val id: String,
    override val image: String?,
    override val name: String,
    override val authorName: String?,
    override val description: String?,
    override val duration: String,
    override val releaseDate: String?,
    override val episodeCount: Int,
) : Card


fun PodcastDto.toCard() = PodcastCard(
    id = podcastId,
    image = image,
    name = name.toString(),
    authorName = null,
    description = description,
    duration = duration.formatDurationToHM(),
    episodeCount = episodeCount,
)

fun EpisodeDto.toCard() = EpisodeCard(
    id = episodeId,
    image = image,
    name = name.toString(),
    authorName = authorName ,
    description = description,
    duration = duration.formatDurationToHM(),
    releaseDate = releaseDate?.getTimeAgo(),
    episodeCount = 0
)

fun AudioBookDto.toCard() = AudioBookCard(
    id = audiobookId,
    image = image,
    name = name.toString(),
    authorName = authorName ,
    description = description,
    duration = duration.formatDurationToHM(),
    releaseDate = releaseDate?.getTimeAgo(),
    episodeCount = 0)

fun AudioArticleDto.toCard() = ArticleCard(
    id = articleId,
    image = image,
    name = name.toString(),
    authorName = authorName ,
    description = description,
    duration = duration.formatDurationToHM(),
    releaseDate = releaseDate?.getTimeAgo(),
    episodeCount = 0
)