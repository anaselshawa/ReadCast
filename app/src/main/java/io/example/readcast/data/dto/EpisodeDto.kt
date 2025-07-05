package io.example.readcast.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto (

  @SerializedName("podcastPopularityScore"      ) var podcastPopularityScore   : Int               = 0,
  @SerializedName("podcastPriority"             ) var podcastPriority          : Int               = 0,
  @SerializedName("episode_id"                  ) var episodeId                : String,
  @SerializedName("name"                        ) var name                     : String?           = null,
  @SerializedName("season_number"               ) var seasonNumber             : String?           = null,
  @SerializedName("episode_type"                ) var episodeType              : String?           = null,
  @SerializedName("podcast_name"                ) var podcastName              : String?           = null,
  @SerializedName("author_name"                 ) var authorName               : String?           = null,
  @SerializedName("description"                 ) var description              : String?           = null,
  @SerializedName("number"                      ) var number                   : String?           = null,
  @SerializedName("duration"                    ) var duration                 : Int               = 0,
  @SerializedName("avatar_url"                  ) var image                    : String?           = null,
  @SerializedName("separated_audio_url"         ) var separatedAudioUrl        : String?           = null,
  @SerializedName("audio_url"                   ) var audioUrl                 : String?           = null,
  @SerializedName("release_date"                ) var releaseDate              : String?           = null,
  @SerializedName("podcast_id"                  ) var podcastId                : String?           = null,
  @SerializedName("chapters"                    ) var chapters                 : ArrayList<String> = arrayListOf(),
  @SerializedName("score"                       ) var score                    : Double            = 0.0

)