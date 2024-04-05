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
        addDismissListener()
    }
    fun showToolTip() {
        tooltipWindow.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
        tooltipWindow.width = ConstraintLayout.LayoutParams.MATCH_PARENT

        tooltipWindow.isOutsideTouchable = true
        tooltipWindow.isTouchable = true

        tooltipWindow.contentView = binding.root
        // Transparent background
        tooltipWindow.setBackgroundDrawable(null)

        val screenPos = IntArray(2)
        // Get location of anchor view on screen
        anchor.getLocationOnScreen(screenPos)

        // Get rect for anchor view
        val anchorRect = Rect(
            screenPos[0], screenPos[1], screenPos[0]
            + anchor.width, screenPos[1] + anchor.height
        )

        val posX = 0
        val posY = anchorRect.bottom + 20
        tooltipWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, posX, posY)

        if (showCheckMark){
            overlayCheckMark(anchor)
        }
    }

    private fun initViews() {
        _binding = ViewTooltipBinding.inflate(LayoutInflater.from(context), null, false)
        // Bind your views here
        binding.apply {
            name.text = data.name
            description.text = data.desc
        }

        checkMarkView.apply {
            setImageResource(R.drawable.ic_check_mark)
        }
    }

    private fun addDismissListener() {
        tooltipWindow.setOnDismissListener {
            _binding = null
        }
    }

    private fun overlayCheckMark(anchor: View) {
        checkMarkWindow.height = 100
        checkMarkWindow.width = 100

        checkMarkWindow.isOutsideTouchable = true
        checkMarkWindow.isTouchable = true
        checkMarkWindow.setBackgroundDrawable(null)

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