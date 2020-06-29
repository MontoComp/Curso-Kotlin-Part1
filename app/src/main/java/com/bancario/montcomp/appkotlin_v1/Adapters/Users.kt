package com.bancario.montcomp.appkotlin_v1.Adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.target.ViewTarget


data class User(
    val user:String?, val password:String?, val name:String?,
    val lastname:String?,
    val dni:String?, val address: String?,
    val image: String?
)