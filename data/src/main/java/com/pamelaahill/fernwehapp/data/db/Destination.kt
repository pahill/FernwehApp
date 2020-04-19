package com.pamelaahill.fernwehapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Destination(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String
)