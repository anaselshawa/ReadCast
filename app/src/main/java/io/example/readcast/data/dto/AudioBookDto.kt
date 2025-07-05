package io.example.readcast.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class AudioBookDto (

  @SerializedName("audiobook_id" ) var audiobookId : String,
  @SerializedName("name"         ) var name        : String? = null,
  @SerializedName("author_name"  ) var authorName  : String? = null,
  @SerializedName("description"  ) var description : String? = null,
  @SerializedName("avatar_url"   ) var image   : String? = null,
  @SerializedName("duration"     ) var duration    : Int     = 0,
  @SerializedName("language"     ) var language    : String? = null,
  @SerializedName("release_date" ) var releaseDate : String? = null,
  @SerializedName("score"        ) var score       : Int     = 0

)