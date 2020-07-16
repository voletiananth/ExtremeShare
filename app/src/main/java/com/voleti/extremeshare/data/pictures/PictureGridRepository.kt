package com.voleti.extremeshare.data.pictures

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import com.google.common.collect.ArrayListMultimap
import com.voleti.extremeshare.data.DynamicRepository
import com.voleti.extremeshare.ui.base.BaseFragment
import com.voleti.extremeshare.ui.model.PictureGrid
import com.voleti.extremeshare.ui.model.PictureGridHeader
import com.voleti.extremeshare.ui.utils.AppUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PictureGridRepository(contentResolver: ContentResolver): DynamicRepository(contentResolver) {

    override val contentUri: Uri
        get() = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    override val projection: Array<String>?
        get() = arrayOf(MediaStore.Images.Media._ID,MediaStore.Images.Media.DATE_MODIFIED)
    override val sortOrder: String?
        get() = "${MediaStore.Images.Media.DATE_MODIFIED} DESC"

    override suspend fun loadData() {
        var tempTabName = 0
        val temp = ArrayListMultimap.create<String, PictureGrid>()
        withContext(dispatcherIo){
           query()?.apply {
               tempTabName = count
                if (moveToFirst()){
                    val id = getColumnIndex(MediaStore.Images.Media._ID)
                    val dateModified = getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED)
                    do {
                        val idUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,getString(id))
                        val date = AppUtils.formatToDate(getLong(dateModified))
                        temp.put(date, PictureGrid(BaseFragment.contentType,idUri))
                    }while (moveToNext())
                }

                close()
            }
       }
        withContext(Dispatchers.Default){
            temp.asMap().forEach{
                tempBaseData.apply {
                    add(PictureGridHeader(BaseFragment.headerType,it.key,tempDynamicData.size))
                    tempDynamicData.add(PictureGridHeader(BaseFragment.contentType,it.key,tempBaseData.size-1))
                    addAll(it.value)
                }

            }
        }
        submitTableName(tempTabName)
        super.loadData()
    }



}