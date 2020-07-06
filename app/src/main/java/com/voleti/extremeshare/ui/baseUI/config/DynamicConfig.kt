package com.voleti.extremeshare.ui.baseUI.config

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.voleti.extremeshare.ui.baseUI.BaseModelImpl

abstract class DynamicConfig: BaseConfig() {
    val subData = mutableListOf<BaseModelImpl>()

    fun  offerSubData(item:BaseModelImpl){
        subData.add(item)
    }
    abstract fun subLayoutManager(context: Context?): RecyclerView.LayoutManager

    abstract fun subViewType(position: Int):Int

    abstract fun subBind(viewDataBinding: ViewDataBinding,position: Int)

}