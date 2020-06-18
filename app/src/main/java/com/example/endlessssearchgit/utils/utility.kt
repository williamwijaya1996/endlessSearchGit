package com.example.endlessssearchgit.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.endlessssearchgit.R

private fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

private fun ImageView.loadImages(url: String?,progressDrawable: CircularProgressDrawable){
    val option = RequestOptions().placeholder(progressDrawable)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun loadImages(view:ImageView, url:String?){
    view.loadImages(url, getProgressDrawable(view.context))
}
