package com.innoventus.swiggydemo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("set_image")
fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}