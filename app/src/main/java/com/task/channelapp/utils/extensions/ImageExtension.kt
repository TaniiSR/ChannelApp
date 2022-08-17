package com.task.channelapp.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(
    resource: String?,
    errorRecourse: Drawable? = null,
    placeRecourse: Drawable? = null
) {
    errorRecourse?.let { err ->
        Glide.with(this).load(resource).placeholder(placeRecourse).error(err)
            .into(this)
    } ?: Glide.with(this).load(resource).into(this)
}
