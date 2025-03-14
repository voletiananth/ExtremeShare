package com.voleti.extremeshare.ui.bindAdapter

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object MediaAdapter {
    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun attachImage(view: ImageView, url: Uri){
        Glide.with(view.context).load(url).override(300).centerCrop().into(view)
//        view.load(url)
    }

}