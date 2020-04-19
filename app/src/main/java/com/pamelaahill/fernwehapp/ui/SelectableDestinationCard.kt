package com.pamelaahill.fernwehapp.ui

import androidx.compose.Composable
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.material.Surface
import com.pamelaahill.fernwehapp.Photo

@Composable
fun SelectablePhoto(photo: Photo, onClick: () -> Unit) {
    Surface(color = if (photo.selected) Color.LightGray else Color.Transparent) {
        Clickable(onClick = onClick) {
            FullDestinationCard(photo = photo)
        }
    }
}