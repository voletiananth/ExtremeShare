package com.voleti.extremeshare.ui.baseUI.config

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.voleti.extremeshare.ui.baseUI.BaseModelImpl
import kotlinx.coroutines.CoroutineScope

abstract class BaseConfig {
    companion object{
        const val headerType = 0
        const val contentType = 1
            }

  val mainData = mutableListOf<BaseModelImpl>()
  abstract fun mainLayoutManager(context: Context?):RecyclerView.LayoutManager



    abstract fun fetchData(lifecycleScope: CoroutineScope,context: Context?)


    protected fun offerMainData(item:BaseModelImpl){
        mainData.add(item)
    }
    abstract fun mainViewType(position:Int):Int
    abstract fun mainBind(viewDataBinding: ViewDataBinding, position: Int)

}