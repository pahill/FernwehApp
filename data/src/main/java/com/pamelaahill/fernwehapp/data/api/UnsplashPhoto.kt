//Copied from https://github.com/unsplash/unsplash-photopicker-android, with thanks

package com.pamelaahill.fernwehapp.data.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val color: String? = "#000000",
    val likes: Int,
    val description: String?,
    val urls: UnsplashUrls,
    val links: UnsplashLinks,
    val user: UnsplashUser,
    var selected: Boolean = false
) : Parcelable