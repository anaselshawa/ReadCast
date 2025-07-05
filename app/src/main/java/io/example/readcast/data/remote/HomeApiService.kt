package io.example.readcast.data.remote

import io.example.readcast.data.dto.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {

    @GET("home_sections")
    suspend fun getHome(@Query("page") page: Int = 0): HomeResponse

}
