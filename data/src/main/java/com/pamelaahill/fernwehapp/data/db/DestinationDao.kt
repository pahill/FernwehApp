package com.pamelaahill.fernwehapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class DestinationDao {

    @Transaction
    open suspend fun insertDestinationWithImages(destination: Destination, destinationImages: List<DestinationImage>){
        insertDestination(destination = destination)
        val destinationId = getDestination(destination.name)?.id!!
        for(destinationImage in destinationImages){
            destinationImage.destinationId = destinationId
            insertDestinationImage(destinationImage = destinationImage)
        }
    }

    @Insert
    abstract fun insertDestination(destination: Destination)

    @Insert
    abstract fun insertDestinationImage(destinationImage: DestinationImage)

    @Transaction
    @Query("SELECT * FROM Destination WHERE name LIKE :name")
    abstract fun getDestination(name: String): Destination?

    @Transaction
    @Query("SELECT * FROM Destination")
    abstract fun getDestinationsWithImages(): LiveData<List<DestinationWithImages>>

    @Transaction
    @Query("SELECT * FROM Destination WHERE name = :destinationName")
    abstract fun getDestinationWithImages(destinationName: String): LiveData<DestinationWithImages>

}