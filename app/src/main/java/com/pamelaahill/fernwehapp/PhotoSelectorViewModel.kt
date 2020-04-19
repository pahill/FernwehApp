package com.pamelaahill.fernwehapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pamelaahill.fernwehapp.data.api.UnsplashPhoto
import com.pamelaahill.fernwehapp.data.db.DestinationDatabase
import com.pamelaahill.fernwehapp.data.repository.SearchResponseRepositoryImpl
import com.pamelaahill.fernwehapp.model.Destination
import com.pamelaahill.fernwehapp.repository.DestinationRepository
import com.pamelaahill.fernwehapp.repository.DestinationRepositoryImpl

class PhotoSelectorViewModel(application: Application) : AndroidViewModel(application) {

    private val photoSearchRepository = SearchResponseRepositoryImpl(getApplication<FernwehApplication>().getString(R.string.unsplash))
    private val photoSearchResults: MutableLiveData<List<UnsplashPhoto>> = MutableLiveData()

    private val photoDatabase: DestinationDatabase = DestinationDatabase.getInstance(application)
    private val destinationRepository: DestinationRepository = DestinationRepositoryImpl(photoDatabase)

    suspend fun searchPhotos(query: String){
        photoSearchResults.postValue(photoSearchRepository.searchPhotos(query).results)
    }

    suspend fun saveDestination(destination: Destination){
        destinationRepository.saveDestination(destination)
    }

    fun getSearchResults(): LiveData<List<UnsplashPhoto>> = photoSearchResults

    class PhotoSelectorViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PhotoSelectorViewModel(application) as T
        }
    }
}