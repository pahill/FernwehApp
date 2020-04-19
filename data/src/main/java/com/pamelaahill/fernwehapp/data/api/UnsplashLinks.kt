//Copied from https://github.com/unsplash/unsplash-photopicker-android, with thanks

package com.pamelaahill.fernwehapp.data.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashLinks(
    val self: String,
    val html: String,
    val photos: String?,
    val likes: String?,
    val portfolio: String?,
    val download: String?,
    val download_location: String?
) : Parcelable