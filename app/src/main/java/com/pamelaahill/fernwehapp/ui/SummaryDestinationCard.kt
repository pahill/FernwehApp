package com.pamelaahill.fernwehapp.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import com.pamelaahill.fernwehapp.model.Destination
import com.pamelaahill.fernwehapp.ui.external.NetworkImagePicasso

@Composable
fun SummaryDestinationCard(destination: Destination, onClick: (Destination) -> Unit) {
    Clickable(onClick = { onClick(destination) }) {
        Card(
            elevation = 10.dp,
            modifier = Modifier.padding(10.dp) + Modifier.fillMaxWidth(),
            color = Color.LightGray
        ) {
            Column {
                NetworkImagePicasso(url = destination.coverImage?.image!!)
                Box(
                    backgroundColor = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    gravity = ContentGravity.TopCenter,
                    padding = 10.dp
                ) {
                    Text(
                        text = destination.name ?: "Untitled",
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}
