package com.pamelaahill.fernwehapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
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
import com.pamelaahill.fernwehapp.ui.external.Hint
import com.pamelaahill.fernwehapp.ui.external.TextFieldWithHint

class NewDestinationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                VerticalScroller {
                    SearchForm()
                }
            }
        }
    }

    @Composable
    fun SearchForm() {
        var destination by state {""}
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Where would you like to go today?", style = MaterialTheme.typography.h4)
            TextFieldWithHint(
                value = destination,
                hint = { Hint(hint = "Take me to...") },
                modifier = Modifier.padding(16.dp),
                onValueChange = {
                    destination = it
                })
            Box(modifier = Modifier.gravity(ColumnAlign.Center)) {
                Button(
                    modifier = Modifier.padding(top = 30.dp),
                    onClick = { search(destination) }) {
                    Text(text = "Search")
                }
            }
        }
    }

    private fun search(destination: String) {
        val intent = Intent(this, PhotoSelectorActivity::class.java)
        intent.putExtra(PhotoSelectorActivity.ARGS_DESTINATION, destination)
        startActivity(intent)
        finish()
    }

}