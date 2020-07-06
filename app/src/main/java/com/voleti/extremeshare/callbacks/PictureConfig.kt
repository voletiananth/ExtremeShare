package com.voleti.extremeshare.callbacks

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.voleti.extremeshare.ui.baseUI.config.DynamicConfig
import kotlinx.coroutines.CoroutineScope

class PictureConfig:DynamicConfig(){
    override fun subLayoutManager(context: Context?): RecyclerView.LayoutManager = LinearLayoutManager(context)

    override fun subViewType(position: Int): Int {
        TODO("")

    }

    override fun subBind(viewDataBinding: ViewDataBinding, position: Int) {
        TODO("Not yet implemented")
    }

    override fun mainLayoutManager(context: Context?): RecyclerView.LayoutManager = GridLayoutManager(context,3).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int = if (mainData[position].viewType== headerType) 1 else spanCount

        }
    }

    override val contentUri: Uri
        get() = TODO("Not yet implemented")

    override fun loadCursor(coroutineScope: CoroutineScope, cursor: Cursor) {
        TODO("Not yet implemented")
    }

    override fun mainViewType(position: Int): Int {
        TODO("Not yet implemented")
    }

    override fun mainBind(viewBinding: ViewBinding, position: Int) {
        TODO("Not yet implemented")


    }

    override val projection: Array<String>?
        get() = super.projection
}