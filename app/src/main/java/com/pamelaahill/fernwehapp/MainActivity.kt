package com.pamelaahill.fernwehapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.preferredSize
import androidx.ui.material.FloatingActionButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.pamelaahill.fernwehapp.model.Destination
import com.pamelaahill.fernwehapp.ui.SummaryDestinationCard
import com.pamelaahill.fernwehapp.ui.external.observe

class MainActivity : AppCompatActivity() {

    private val viewModel  by lazy {
        ViewModelProvider(this, MainActivityViewModel.MainActivityViewModelFactory(application)).get(
            MainActivityViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Scaffold(floatingActionButton = {
                    FloatingActionButton(
                        onClick = ::openNewDestination,
                        modifier = Modifier.preferredSize(50.dp)
                    ) {
                        Icon(asset = vectorResource(R.drawable.ic_baseline_add_location_24))
                    }
                },
                    floatingActionButtonPosition = Scaffold.FabPosition.End,
                    bodyContent = {
                        VerticalScroller {
                            Column {
                                val destinations = observe(createTestData())
                                destinations?.let {
                                    for (destination in it) {
                                        SummaryDestinationCard(destination = destination, onClick = {
                                            openDestination(it.name)
                                        })
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    private fun openNewDestination() {
        val intent = Intent(this, NewDestinationActivity::class.java)
        startActivity(intent)
    }

    private fun openDestination(destinationName: String){
        val intent = Intent(this, ViewDestinationActivity::class.java)
        intent.putExtra(ViewDestinationActivity.ARGS_DESTINATION_NAME, destinationName)

        startActivity(intent)
    }

    private fun createTestData(): LiveData<List<Destination>> {
        return viewModel.getDestinations()
    }
}