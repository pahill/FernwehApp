//Copied from https://stackoverflow.com/questions/58883218/textfield-with-hint-text-in-jetpack-compose, with thanks

package com.pamelaahill.fernwehapp.ui.external

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.TextField
import androidx.ui.foundation.currentTextStyle
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.input.VisualTransformation
import androidx.ui.layout.Stack
import androidx.ui.layout.padding
import androidx.ui.material.Surface
import androidx.ui.text.TextLayoutResult
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp


@Composable
fun TextFieldWithHint(
    value: String,
    modifier: Modifier = Modifier.None,
    hint: @Composable() () -> Unit,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = currentTextStyle(),
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Unspecified,
    onFocus: () -> Unit = {},
    onBlur: () -> Unit = {},
    focusIdentifier: String? = null,
    onImeActionPerformed: (ImeAction) -> Unit = {},
    visualTransformation: VisualTransformation? = null,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Surface(
        color = Color.LightGray,
        modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp)
    ) {
        Stack() {
            TextField(
                value = value,
                modifier = modifier,
                onValueChange = onValueChange,
                textStyle = textStyle,
                keyboardType = keyboardType,
                imeAction = imeAction,
                onFocus = onFocus,
                onBlur = onBlur,
                focusIdentifier = focusIdentifier,
                onImeActionPerformed = onImeActionPerformed,
                visualTransformation = visualTransformation,
                onTextLayout = onTextLayout
            )
            if (value.isEmpty()) hint()
        }
    }
}