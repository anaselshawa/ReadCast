package io.example.readcast.data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Layout(val raw: String) {
    @SerialName("square")
    SQUARE("square"),

    @SerialName("queue")
    QUEUE("queue"),

    @SerialName("big_square")
    BIG_SQUARE("big_square"),

    @SerialName("2_lines_grid")
    TWO_LINES_GRID("2_lines_grid");

    companion object {
        fun from(raw: String): Layout? =
            entries.firstOrNull { it.raw == raw }
    }
}