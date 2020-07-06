package com.voleti.extremeshare.callbacks

import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.voleti.extremeshare.ui.baseUI.BaseModelImpl
import com.voleti.extremeshare.ui.baseUI.config.BaseConfig
import com.voleti.extremeshare.ui.datamodels.Header
import com.voleti.extremeshare.ui.datamodels.PictureContent
import kotlinx.coroutines.CoroutineScope

class PictureConfig:BaseConfig(){
    override fun mainLayoutManager(context: Context?): RecyclerView.LayoutManager {
        TODO("Not yet implemented")
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