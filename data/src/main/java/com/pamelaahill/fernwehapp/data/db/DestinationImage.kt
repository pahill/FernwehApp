package com.pamelaahill.fernwehapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DestinationImage(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var destinationId: Long,
    val title: String,
    val photographer: String,
    val image: String
)