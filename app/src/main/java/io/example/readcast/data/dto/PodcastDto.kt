package io.example.readcast.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class PodcastDto (

  @SerializedName("podcast_id"      ) var podcastId       : String,
  @SerializedName("name"            ) var name            : String? = null,
  @SerializedName("description"     ) var description     : String? = null,
  @SerializedName("avatar_url"      ) var image       : String? = null,
  @SerializedName("episode_count"   ) var episodeCount    : Int     = 0,
  @SerializedName("duration"        ) var duration        : Int     = 0,
  @SerializedName("language"        ) var language        : String? = null,
  @SerializedName("priority"        ) var priority        : Int     = 0,
  @SerializedName("popularityScore" ) var popularityScore : Int     = 0,
  @SerializedName("score"           ) var score           : Double? = null

)

