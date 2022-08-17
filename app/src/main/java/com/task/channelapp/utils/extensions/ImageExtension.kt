package com.task.channelapp.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.task.channelapp.R


fun ImageView.loadImage(
    resource: String?,
    errorRecourse: Drawable? = null,
    placeRecourse: Drawable? = null
) {
    errorRecourse?.let { err ->
        Glide.with(this).load(resource).placeholder(
            placeRecourse ?: ContextCompat.getDrawable(
                this.context,
                R.drawable.ic_mindvalley
            )
        ).error(err)
            .into(this)
    } ?: Glide.with(this).load(resource).into(this)
}
