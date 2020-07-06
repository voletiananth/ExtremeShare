package com.voleti.extremeshare.ui.baseUI.config

import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.voleti.extremeshare.ui.baseUI.BaseModelImpl

abstract class BaseConfig< M:BaseModelImpl > {
    companion object{
        const val headerType = 0
        const val contentType = 1
            }

   val mainData = mutableListOf<M>()
  abstract fun mainLayoutManager(context: Context?):RecyclerView.LayoutManager
    abstract val contentUri:Uri
    open  val projection:Array<String>? = null
    open val selection:String? = null
    open val selectionArgs:Array<String>? = null
    open val selectOrder:String? = null

    abstract fun loadCursor()

    protected fun offerMainData(item:M){
        mainData.add(item)
    }
    abstract fun mainViewType(position:Int):Int
    abstract fun mainBind(viewBinding: ViewBinding, position: Int)

}