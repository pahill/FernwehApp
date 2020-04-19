package com.pamelaahill.fernwehapp.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import com.pamelaahill.fernwehapp.Photo
import com.pamelaahill.fernwehapp.ui.external.NetworkImagePicasso

@Composable
fun FullDestinationCard(photo: Photo){
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(10.dp) + Modifier.fillMaxWidth(),
        color = Color.LightGray
    ) {
        Column {
            NetworkImagePicasso(url = photo.url)
            Box(
                backgroundColor = Color.White,
                modifier = Modifier.fillMaxWidth(),
                gravity = ContentGravity.TopCenter,
                padding = 10.dp
            ) {
                Text(
                    text = photo.title,
                    style = MaterialTheme.typography.h6
                )
            }
            Box(
                backgroundColor = Color.White,
                modifier = Modifier.fillMaxWidth(),
                gravity = ContentGravity.TopCenter,
                paddingStart = 10.dp,
                paddingBottom = 10.dp,
                paddingEnd = 10.dp
            ) {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = photo.photographer,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}