package com.voleti.extremeshare.ui.baseUI.config

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.voleti.extremeshare.ui.baseUI.BaseModelImpl
import kotlinx.coroutines.CoroutineScope

abstract class BaseConfig {
    companion object{
        const val headerType = 0
        const val contentType = 1
            }

  val mainData = mutableListOf<BaseModelImpl>()
  abstract fun mainLayoutManager(context: Context?):RecyclerView.LayoutManager

    abstract val contentUri:Uri
    open  val projection:Array<String>? = null
    open val selection:String? = null
    open val selectionArgs:Array<String>? = null
    open val selectOrder:String? = null

    abstract fun loadCursor(coroutineScope: CoroutineScope,cursor: Cursor)

    protected fun offerMainData(item:BaseModelImpl){
        mainData.add(item)
    }
    abstract fun mainViewType(position:Int):Int
    abstract fun mainBind(viewBinding: ViewBinding, position: Int)

}