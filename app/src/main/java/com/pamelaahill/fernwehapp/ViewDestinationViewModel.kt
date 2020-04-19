package com.pamelaahill.fernwehapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pamelaahill.fernwehapp.data.db.DestinationDatabase
import com.pamelaahill.fernwehapp.repository.DestinationRepository
import com.pamelaahill.fernwehapp.repository.DestinationRepositoryImpl

class ViewDestinationViewModel(application: Application) : AndroidViewModel(application) {

    private val photoDatabase: DestinationDatabase = DestinationDatabase.getInstance(application)
    private val destinationRepository: DestinationRepository = DestinationRepositoryImpl(photoDatabase)

    fun searchDestination(destinationName: String) = destinationRepository.searchDestination(destinationName)

    class ViewDestinationViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ViewDestinationViewModel(application) as T
        }
    }
}