package com.pamelaahill.fernwehapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("/search/photos")
    suspend fun searchPhotos(@Query("query") query: String): SearchResponse

}