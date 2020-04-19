package com.pamelaahill.fernwehapp.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class DestinationWithImages(
    @Embedded val destination: Destination,
    @Relation(parentColumn = "id", entityColumn = "destinationId")
    val images: List<DestinationImage>
)