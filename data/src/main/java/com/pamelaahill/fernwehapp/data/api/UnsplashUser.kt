//Copied from https://github.com/unsplash/unsplash-photopicker-android, with thanks

package com.pamelaahill.fernwehapp.data.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashUser(
    val id: String,
    val username: String,
    val name: String,
    val portfolio_url: String?,
    val bio: String?,
    val location: String?,
    val total_likes: Int,
    val total_photos: Int,
    val total_collection: Int,
    val profile_image: UnsplashUrls,
    val links: UnsplashLinks
) : Parcelable