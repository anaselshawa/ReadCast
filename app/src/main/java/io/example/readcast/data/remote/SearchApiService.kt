package io.example.readcast.data.remote

import io.example.readcast.data.dto.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search")
    suspend fun search(@Query("query") query: String): HomeResponse

}
