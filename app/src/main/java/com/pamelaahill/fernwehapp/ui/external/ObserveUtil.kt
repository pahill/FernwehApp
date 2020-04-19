//Copied from https://stackoverflow.com/questions/59096435/using-livedata-as-state-inside-jetpack-compose-functions, with thanks

package com.pamelaahill.fernwehapp.ui.external

import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@Composable
fun <T> observe(data: LiveData<T>): T? {
    var result by state { data.value }
    val observer = remember { Observer<T> { result = it } }

    onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    return result
}