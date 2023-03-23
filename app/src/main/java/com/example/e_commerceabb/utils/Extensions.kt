package com.example.e_commerceabb.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File

fun ImageView.load(string: String) {
    Glide.with(context)
        .load(string)
        .into(this);
}
fun ImageView.load(uri: Uri) {
    Glide.with(context)
        .load(uri)
        .into(this);
}
fun ImageView.load(drawable: Drawable) {
    Glide.with(context)
        .load(drawable)
        .into(this);
}
fun ImageView.load(file: File) {
    Glide.with(context)
        .load(file)
        .into(this);
}
fun ImageView.load(drawable: Int) {
    Glide.with(context)
        .load(drawable)
        .into(this);
}
