package com.voleti.extremeshare.callbacks

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.text.format.DateUtils
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.common.collect.ArrayListMultimap
import com.voleti.extremeshare.BR
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.baseUI.config.DynamicConfig
import com.voleti.extremeshare.ui.dataModels.Header
import com.voleti.extremeshare.ui.dataModels.PictureContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PictureGrid(block:(Int)->Unit):DynamicConfig(block){
    override fun subLayoutManager(context: Context?): RecyclerView.LayoutManager = LinearLayoutManager(context)

    override fun subViewType(position: Int): Int = when(subData[position].viewType){
        contentType->R.layout.item_view_header
        else->R.layout.item_view_empty
    }



    override fun subBind(viewDataBinding: ViewDataBinding, position: Int) {
       if(subData[position].viewType== contentType)
           viewDataBinding.setVariable(BR.header,subData[position] as Header)
    }

    override fun mainLayoutManager(context: Context?): RecyclerView.LayoutManager = GridLayoutManager(context,3).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int = if (mainData[position].viewType== headerType) 1 else spanCount

        }
    }





    override fun mainViewType(position: Int): Int = when(mainData[position].viewType) {
        contentType -> R.layout.item_view_image_content
        headerType -> R.layout.item_view_header
        else -> R.layout.item_view_empty
    }

    override fun mainBind(viewDataBinding: ViewDataBinding, position: Int) {
        when(mainData[position].viewType){
            contentType -> viewDataBinding.setVariable( BR.image , mainData[position] as PictureContent)
            headerType -> viewDataBinding.setVariable( BR.header , mainData[position] as Header)
        }



    }


    override fun fetchData(lifecycleScope: CoroutineScope, context: Context,dataSet:()->Unit) {

        lifecycleScope.launch(Dispatchers.IO) {
            context.contentResolver?.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Images.Media._ID,MediaStore.Images.Media.DATE_MODIFIED),
                null,
                null,
                "${MediaStore.Images.Media.DATE_MODIFIED} DESC"
            )?.apply {
                withContext(Dispatchers.Main){
                    tabName(count)
                }

                if (moveToFirst()) do {

                    val id = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,getLong(getColumnIndex(MediaStore.Images.Media._ID)).toString())

                    val date =  DateUtils.formatDateTime(context,getLong(getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED)),DateUtils.FORMAT_NO_MONTH_DAY)
                        //TODO("Change Date Format")
                    tempData.put(date, PictureContent(contentType,id))

                }while (moveToNext())

                close()
            }

            withContext(Dispatchers.Default){
                tempData.asMap().forEach {
                    mainData.apply {
                        add(Header(headerType,it.key))
                        addAll(it.value)
                    }
                    subData.add(Header(contentType,it.key))

                }
            }
            withContext(Dispatchers.Main){
                dataSet()
            }


        }
    }

    private val tempData = ArrayListMultimap.create<String,PictureContent>()


}