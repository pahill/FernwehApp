package com.pamelaahill.fernwehapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.ColumnAlign
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import com.pamelaahill.fernwehapp.data.api.UnsplashPhoto
import com.pamelaahill.fernwehapp.model.Destination
import com.pamelaahill.fernwehapp.model.DestinationImage
import com.pamelaahill.fernwehapp.ui.external.observe
import com.pamelaahill.fernwehapp.ui.SelectablePhoto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PhotoSelectorActivity : AppCompatActivity() {

        private val viewModel  by lazy {
            ViewModelProvider(this, PhotoSelectorViewModel.PhotoSelectorViewModelFactory(application)).get(
                PhotoSelectorViewModel::class.java
            )
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val destinationName = intent.getStringExtra(ARGS_DESTINATION)

        val mainActivityJob = Job()
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch {
            viewModel.searchPhotos(destinationName!!)
        }

        setContent {
            MaterialTheme {
                PhotoSelectorForm(
                    destinationName = destinationName,
                    photos = viewModel.getSearchResults(),
                    onClick = { save(it) })
            }
        }
    }

    private fun save(destination: Destination) {
        val mainActivityJob = Job()
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch {
            viewModel.saveDestination(destination)
            finish()
        }
    }

    companion object {
        const val ARGS_DESTINATION = "args_destination"
    }
}

@Model
data class Photo(
    var selected: Boolean = false,
    val url: String,
    val title: String,
    val photographer: String
)

@Composable
fun SelectablePhotos(photos: List<UnsplashPhoto>?, addDestinationImage: (DestinationImage) -> Unit, removeDestinationImage: (String) -> Unit) {
    Column {
        photos?.let {
            for (unsplashPhoto in it) {
                val photo = Photo(
                    false,
                    unsplashPhoto.urls.medium ?: unsplashPhoto.urls.small,
                    unsplashPhoto.description ?: "Untitled",
                    unsplashPhoto.user.name ?: "Anonymous"
                )
                SelectablePhoto(photo) {
                    photo.selected = !photo.selected
                    if (photo.selected) {
                        val destinationImage = DestinationImage(
                            unsplashPhoto.id,
                            photo.title,
                            photo.photographer,
                            photo.url
                        )
                        addDestinationImage(destinationImage)
                    } else {
                        removeDestinationImage(unsplashPhoto.id)
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoSelectorForm(
    destinationName: String,
    photos: LiveData<List<UnsplashPhoto>>,
    onClick: (Destination) -> Unit
) {
    val searchResultsPhotos =
        observe(photos)
    val selectedPhotos = mutableListOf<DestinationImage>()
    val destination = Destination(destinationName, null, selectedPhotos)
    VerticalScroller {
        Column (modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Visiting ${destination.name.toUpperCase()}",
                style = MaterialTheme.typography.h4
            )
            SelectablePhotos(photos = searchResultsPhotos, addDestinationImage = {
                selectedPhotos.add(it)
            }, removeDestinationImage = {
                selectedPhotos.removeAll { destinationImage ->  destinationImage.unsplashId == it }
            })
            Box(modifier = Modifier.gravity(ColumnAlign.Center)) {
                Button(
                    modifier = Modifier.padding(30.dp),
                    onClick = { onClick(destination) }) {
                    Text(text = "Save")
                }
            }
        }
    }
}