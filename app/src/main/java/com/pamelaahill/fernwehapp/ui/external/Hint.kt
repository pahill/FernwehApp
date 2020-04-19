//Copied from https://stackoverflow.com/questions/58883218/textfield-with-hint-text-in-jetpack-compose, with thanks

package com.pamelaahill.fernwehapp.ui.external

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.padding
import androidx.ui.unit.dp

@Composable
fun Hint(hint: String) {
    Text(text = hint, modifier = Modifier.padding(16.dp))
}