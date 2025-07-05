package io.example.readcast.data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class ContentType(val raw: String) {
    @SerialName("podcast")
    PODCAST("podcast"),

    @SerialName("episode")
    EPISODE("episode"),

    @SerialName("audio_book")
    AUDIO_BOOK("audio_book"),

    @SerialName("audio_article")
    AUDIO_ARTICLE("audio_article");

    companion object {
        fun from(raw: String): ContentType? =
            entries.firstOrNull { it.raw == raw }
    }
}