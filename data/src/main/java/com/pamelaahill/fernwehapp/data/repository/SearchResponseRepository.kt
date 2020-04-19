package com.pamelaahill.fernwehapp.data.repository

import com.pamelaahill.fernwehapp.data.api.SearchResponse

interface SearchResponseRepository {

    suspend fun searchPhotos(query: String): SearchResponse

}