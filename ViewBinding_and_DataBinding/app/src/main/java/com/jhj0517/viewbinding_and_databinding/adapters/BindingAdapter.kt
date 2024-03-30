package com.jhj0517.viewbinding_and_databinding.adapters

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * The attribute name used in the XML is specified within the BindingAdapter annotation,
 * in this case, "isVisible". Utilize variables within the function as needed for your logic.
 *
 * @param view The view whose visibility is being bound.
 * @param isVisible The Boolean value that determines the view's visibility. If true, the view is made visible;
 *                  if false or null, the view is made invisible.
 */
@BindingAdapter("isVisible")
fun bindIsVisible(view: View, isVisible: Boolean?){
    view.visibility =if (isVisible==true){
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}
