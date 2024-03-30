package com.jhj0517.viewbinding_and_databinding.adapters

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("isVisible")
fun bindIsVisible(view: View, isVisible: Boolean?){
    view.visibility =if (isVisible==true){
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}
