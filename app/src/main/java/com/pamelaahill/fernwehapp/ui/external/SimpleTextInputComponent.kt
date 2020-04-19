//Copied from https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example, with thanks

package com.pamelaahill.fernwehapp.ui.external

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.TextField
import androidx.ui.graphics.Color
import androidx.ui.layout.padding
import androidx.ui.material.Surface
import androidx.ui.unit.dp

@Composable
fun SimpleTextInputComponent(hint: String, text: String, onValueChanged: (String) -> Unit) {
    // Surface is a composable provided to fulfill the needs of the "Surface" metaphor from the
    // Material Design specification. It's generally used to change the background color, add
    // elevation, clip or add background shape to its children composables.

    // You can think of Modifiers as implementations of the decorators pattern that are used to
    // modify the composable that its applied to. In this example, we assign a padding of
    // 16dp to the Surface.
    Surface(
        color = Color.LightGray,
        modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp)
    ) {
        // TextField is a composable that is capable of accepting text user input. It renders the
        // value that you pass to the "value" field. In order to update this as the user is
        // typing a new string, we make use of the state delegate. Reacting to state changes is
        // the core behavior of Compose. Any composable that reads the value of the textValue
        // field will recompose whenever this value is changed. In this example, since the
        // TextField is reading the value from the textValue value, and that's also the value
        // that we update as the user types (through the onValueChange lambda), this composable
        // is redrawn and updated with the latest value.
        var destinationState by state {
            if (text.isEmpty()) {
                hint
            } else {
                text
            }
        }

        TextField(value = destinationState,
            modifier = Modifier.padding(16.dp),
            // Update value of textValue with the latest value of the text field
            onValueChange = {
                destinationState = it
                onValueChanged(it)
            }
        )
    }
}