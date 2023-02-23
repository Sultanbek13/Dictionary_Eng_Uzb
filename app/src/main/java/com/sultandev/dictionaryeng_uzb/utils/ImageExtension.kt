package com.sultandev.dictionaryeng_uzb.utils

import android.widget.ImageView
import com.sultandev.dictionaryeng_uzb.R

fun setSelectImage(state: Boolean, img: ImageView) {
    if(state) img.setImageResource(R.drawable.star_true)
    else img.setImageResource(R.drawable.star_false)
}