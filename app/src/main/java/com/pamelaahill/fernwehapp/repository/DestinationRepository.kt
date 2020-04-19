package com.pamelaahill.fernwehapp.repository

import androidx.lifecycle.LiveData
import com.pamelaahill.fernwehapp.model.Destination
import com.pamelaahill.fernwehapp.model.DestinationImage

interface DestinationRepository {

    fun getDestinations(): LiveData<List<Destination>>
    fun searchDestination(name: String): LiveData<List<DestinationImage>>

    suspend fun saveDestination(destination: Destination)

}