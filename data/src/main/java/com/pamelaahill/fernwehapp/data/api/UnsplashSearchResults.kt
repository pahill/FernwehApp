//Copied from https://github.com/unsplash/unsplash-photopicker-android, with thanks

package com.pamelaahill.fernwehapp.data.api

data class SearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<UnsplashPhoto>
)