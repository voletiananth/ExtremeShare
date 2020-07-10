package com.voleti.extremeshare.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout


class SquareLayout  @JvmOverloads constructor(context: Context,attributes: AttributeSet?=null,defAttrStyle:Int=0):RelativeLayout(context,attributes,defAttrStyle) {
    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, widthSpec)
    }
}