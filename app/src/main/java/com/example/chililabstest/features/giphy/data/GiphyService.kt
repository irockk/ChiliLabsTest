package com.example.chililabstest.features.giphy.data

import com.example.chililabstest.features.giphy.data.models.GifRemoteModel
import com.example.chililabstest.features.giphy.data.models.GifResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {
    @GET("trending")
    suspend fun getGifsPaged(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String = "g"
    ): Response<GifResponse>
}