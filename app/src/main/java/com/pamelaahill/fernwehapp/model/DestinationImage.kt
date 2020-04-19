package com.pamelaahill.fernwehapp.model

import androidx.compose.Model

@Model
data class DestinationImage(val unsplashId: String, val title: String, val photographer: String, val image: String)