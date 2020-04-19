package com.pamelaahill.fernwehapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import com.pamelaahill.fernwehapp.model.DestinationImage
import com.pamelaahill.fernwehapp.ui.FullDestinationCard
import com.pamelaahill.fernwehapp.ui.external.observe

class ViewDestinationActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, ViewDestinationViewModel.ViewDestinationViewModelFactory(application)).get(
            ViewDestinationViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val destinationName = intent.getStringExtra(ARGS_DESTINATION_NAME)!!

        setContent {
            MaterialTheme {
                VerticalScroller {
                    blah(viewModel.searchDestination(destinationName))
                }
            }
        }
    }

    companion object {
        const val ARGS_DESTINATION_NAME = "args_destination"
    }
}

@Composable
fun blah(images: LiveData<List<DestinationImage>>) {
    val destinationImages = observe(images)
    Column {
        if(destinationImages != null) {
            for (destinationImage in destinationImages) {
                val photo = Photo(
                    false,
                    destinationImage.image,
                    destinationImage.title,
                    destinationImage.photographer
                )
                FullDestinationCard(photo = photo)
            }
        }
    }
}
