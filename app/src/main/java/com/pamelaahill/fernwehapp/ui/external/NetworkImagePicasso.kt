//Copied and slightly modified from https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example, with thanks

package com.pamelaahill.fernwehapp.ui.external

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Image
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.graphics.ScaleFit
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.preferredHeightIn
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.pamelaahill.fernwehapp.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

@Composable
fun NetworkImagePicasso(url: String) {
    // Source code inspired from - https://kotlinlang.slack.com/archives/CJLTWPH7S/p1573002081371500.
    // Made some minor changes to the code Leland posted.
    var image by state<AndroidImageAsset?> { null }
    var drawable by state<Drawable?> { null }
    onCommit(url) {
        val picasso = Picasso.get()
        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // TODO(lmr): we could use the drawable below
                drawable = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                drawable = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image = bitmap?.let {
                    AndroidImageAsset(
                        it
                    )
                }
            }
        }
        picasso
            .load(url)
            .placeholder(R.drawable.ic_baseline_location_city_24)
            .error(R.drawable.ic_baseline_location_city_24)
            .into(target)

        onDispose {
            image = null
            drawable = null
            picasso.cancelRequest(target)
        }
    }

    val theImage = image
    val theDrawable = drawable
    if (theImage != null) {
        // Box is a predefined convenience composable that allows you to apply common draw & layout
        // logic. In addition we also pass a few modifiers to it.

        // You can think of Modifiers as implementations of the decorators pattern that are
        // used to modify the composable that its applied to. In this example, we configure the
        // Box composable to have a max height of 200dp and fill out the entire available
        // width.
        Box(modifier = Modifier.fillMaxWidth() + Modifier.preferredHeightIn(maxHeight = 300.dp)) {
            // Image is a pre-defined composable that lays out and draws a given [ImageAsset].
            Image(
                asset = theImage,
                scaleFit = ScaleFit.FillWidth,
                modifier = Modifier.fillMaxWidth() + Modifier.preferredHeightIn(
                    minHeight = 200.dp,
                    maxHeight = 300.dp
                )
            )
        }
    } else if (theDrawable != null) {
        Box(
            modifier = Modifier.fillMaxWidth() + Modifier.preferredHeightIn(maxHeight = 300.dp),
            gravity = ContentGravity.Center
        ) {
            Image(
                asset = vectorResource(id = R.drawable.ic_baseline_location_city_24),
                colorFilter = ColorFilter.tint(Color.Gray),
                scaleFit = ScaleFit.Fit
            )
        }
    }
}