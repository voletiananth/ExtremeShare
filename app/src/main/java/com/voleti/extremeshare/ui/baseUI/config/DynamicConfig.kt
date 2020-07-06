package com.voleti.extremeshare.ui.baseUI.config

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.voleti.extremeshare.ui.baseUI.BaseModelImpl

abstract class DynamicConfig<D:BaseModelImpl,B:BaseModelImpl>:BaseConfig<B>(){
    private val subData = mutableListOf<D>()

    fun  offerSubData(item:D){
        subData.add(item)
    }

    abstract fun subLayoutManager(context: Context?): RecyclerView.LayoutManager

    abstract fun subViewType(position: Int):Int

    abstract fun subBind(viewDataBinding: ViewDataBinding,position: Int)

}