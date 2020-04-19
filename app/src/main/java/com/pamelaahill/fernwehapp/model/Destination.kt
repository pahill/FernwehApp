package com.pamelaahill.fernwehapp.model

import androidx.compose.Model

@Model
data class Destination(
    val name: String,
    val coverImage: DestinationImage?,
    val images: MutableList<DestinationImage>
)