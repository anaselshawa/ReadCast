package io.example.readcast.data.dto

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SectionDto(
    val name: String,
    val type: String,
    val order: Int,
    @SerializedName("content_type")
    val contentType: String,
    val content: JsonArray,
)