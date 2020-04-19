package com.pamelaahill.fernwehapp.data.repository

import com.pamelaahill.fernwehapp.data.api.SearchResponse
import com.pamelaahill.fernwehapp.data.api.UnsplashApi
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SearchResponseRepositoryImpl(unsplashApiKey: String) : SearchResponseRepository {

    private val unsplashApi: UnsplashApi

    init {
        val httpClient = OkHttpClient.Builder().addInterceptor {
            val newRequest: Request = it.request().newBuilder()
                .addHeader("Authorization", "Client-ID $unsplashApiKey")
                .build()
            it.proceed(newRequest)
        }
            .build()

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        unsplashApi = retrofit.create(UnsplashApi::class.java)
    }

    override suspend fun searchPhotos(query: String): SearchResponse {
        return unsplashApi.searchPhotos(query)
    }

    companion object {
        const val BASE_URL = "https://api.unsplash.com/v1/"
    }
}