package com.voleti.extremeshare.ui.baseUI.config

import android.net.Uri
import com.voleti.extremeshare.ui.baseUI.BaseModelImpl

abstract class BaseConfig<M:BaseModelImpl> {
    companion object{
        const val headerType = 0
        const val contentType = 1
            }

   val mainData = mutableListOf<M>()

    abstract val contentUri:Uri
    open  val projection:Array<String>? = null
    open val selection:String? = null
    open val selectionArgs:Array<String>? = null
    open val selectOrder:String? = null

    abstract fun mainCursor()

    protected fun offerMainData(item:M){
        mainData.add(item)
    }

}