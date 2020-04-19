package com.pamelaahill.fernwehapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pamelaahill.fernwehapp.data.db.DestinationDatabase
import com.pamelaahill.fernwehapp.data.db.DestinationImage
import com.pamelaahill.fernwehapp.model.Destination

class DestinationRepositoryImpl(private val destinationDatabase: DestinationDatabase) : DestinationRepository{

    override fun getDestinations(): LiveData<List<Destination>> {
        return Transformations.map(
            destinationDatabase.destinationDao().getDestinationsWithImages()
        ) {
            it.map { destinationWithImages ->
                val destinationImages = destinationWithImages.images.map { destinationImage ->
                    com.pamelaahill.fernwehapp.model.DestinationImage(
                        "unknown",
                        destinationImage.title,
                        destinationImage.photographer,
                        destinationImage.image
                    )
                }
                Destination(
                    destinationWithImages.destination.name,
                    destinationImages.firstOrNull(),
                    destinationImages.toMutableList()
                )
            }
        }
    }

    override fun searchDestination(name: String): LiveData<List<com.pamelaahill.fernwehapp.model.DestinationImage>> {
        return Transformations.map(
            destinationDatabase.destinationDao().getDestinationWithImages(name)
        ) {
            val images = it.images.map { destinationImage ->
                com.pamelaahill.fernwehapp.model.DestinationImage(
                    unsplashId = "unknown",
                    title = destinationImage.title,
                    photographer = destinationImage.photographer,
                    image = destinationImage.image
                )
            }
            images
        }
    }

    override suspend fun saveDestination(destination: Destination) {
        val destinationEntity =
            com.pamelaahill.fernwehapp.data.db.Destination(id = 0, name = destination.name)
        val destinationImages = destination.images.map {
            DestinationImage(
                id = 0,
                destinationId = 0,
                title = it.title,
                photographer = it.photographer,
                image = it.image
            )
        }
        destinationDatabase.destinationDao()
            .insertDestinationWithImages(destinationEntity, destinationImages)
    }
}