//Copied from https://github.com/unsplash/unsplash-photopicker-android, with thanks

package com.pamelaahill.fernwehapp.data.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashUrls(
    val thumb: String?,
    val small: String,
    val medium: String?,
    val regular: String?,
    val large: String?,
    val full: String?,
    val raw: String?
) : Parcelable