package com.jhj0517.custom_tooltip_window.views

import android.content.Context
import android.graphics.Rect
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import com.jhj0517.custom_tooltip_window.R
import com.jhj0517.custom_tooltip_window.databinding.ViewTooltipBinding
import com.jhj0517.custom_tooltip_window.models.ExampleData

const val TOOLTIP_BOTTOM_MARGIN = 20

class TooltipView(
    private val context: Context,
    private val data: ExampleData,
    private val anchor: View,
    private val showCheckMark: Boolean = true
) {

    private val tooltipWindow: PopupWindow = PopupWindow(context)
    private val checkMarkWindow: PopupWindow = PopupWindow(context)

    private var _binding: ViewTooltipBinding? = null
    private val binding get() = _binding!!
    private val checkMarkView: ImageView = ImageView(context)

    init {
        initViews()
        addAnimation()
        addDismissListener()
    }

    fun showToolTip() {
        // Get location of anchor view on screen
        val screenPos = IntArray(2)
        anchor.getLocationOnScreen(screenPos)

        // Get rect with same size as anchor view
        val anchorRect = Rect(
            screenPos[0], screenPos[1], screenPos[0]
                    + anchor.width, screenPos[1] + anchor.height
        )

        // Define where to show tooltip
        val posX = 0
        val posY = anchorRect.bottom + TOOLTIP_BOTTOM_MARGIN

        tooltipWindow.contentView = binding.root
        tooltipWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, posX, posY)

        if (showCheckMark) {
            overlayCheckMark(anchor)
        }
    }

    private fun initViews() {
        _binding = ViewTooltipBinding.inflate(LayoutInflater.from(context), null, false)
        // Bind your view with data class
        binding.apply {
            name.text = data.name
            description.text = data.desc
        }

        tooltipWindow.apply {
            height = ConstraintLayout.LayoutParams.WRAP_CONTENT
            width = ConstraintLayout.LayoutParams.MATCH_PARENT
            isOutsideTouchable = true
            isTouchable = true
            isFocusable = true
            // Transparent background
            setBackgroundDrawable(null)
        }

        checkMarkView.apply {
            setImageResource(R.drawable.ic_check_mark)
        }

        checkMarkWindow.apply {
            height = 100
            width = 100
            setBackgroundDrawable(null)
        }
    }

    private fun addAnimation(){
        // You can add your own animation here
        tooltipWindow.animationStyle = androidx.appcompat.R.style.Animation_AppCompat_DropDownUp
        // Or you can set enter / exit transition as you want also
        tooltipWindow.enterTransition = null
        tooltipWindow.exitTransition = null

        checkMarkWindow.animationStyle = androidx.appcompat.R.style.Animation_AppCompat_Dialog
    }

    private fun addDismissListener() {
        tooltipWindow.setOnDismissListener {
            // Binding to null when dismiss to avoid memory leak
            _binding = null
            // Dismiss check mark together
            if(checkMarkWindow.isShowing){
                checkMarkWindow.dismiss()
            }
        }
    }

    private fun overlayCheckMark(anchor: View) {
        val screenPos = IntArray(2)

        anchor.getLocationOnScreen(screenPos)

        val anchorRect = Rect(
            screenPos[0], screenPos[1], screenPos[0]
            + anchor.width, screenPos[1] + anchor.height
        )

        // center X
        val posX = (anchorRect.centerX() - checkMarkWindow.width/2)
        // center Y
        val posY = (anchorRect.centerY() - checkMarkWindow.height/2)
        checkMarkWindow.contentView = checkMarkView
        checkMarkWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, posX, posY)
    }

    fun isTooltipShown(): Boolean {
        return tooltipWindow.isShowing
    }
    fun dismissTooltip() {
        tooltipWindow.dismiss()
        checkMarkWindow.dismiss()
    }

}