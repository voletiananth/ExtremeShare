package com.voleti.extremeshare.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import com.google.common.collect.ArrayListMultimap
import com.voleti.extremeshare.ui.base.BaseFragment
import com.voleti.extremeshare.ui.model.ItemType
import com.voleti.extremeshare.ui.model.PictureGrid
import com.voleti.extremeshare.ui.model.PictureHeader
import com.voleti.extremeshare.ui.utils.AppUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalMedia @Inject constructor(private val contentResolver: ContentResolver) {


    private val dispatcherIo = Dispatchers.IO

    suspend fun imagesTimeline():List<ItemType> {

        val temp = ArrayListMultimap.create<String, PictureGrid>()
        withContext(dispatcherIo){
            contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATE_MODIFIED),
                null,
                null,
                "${MediaStore.Images.Media.DATE_MODIFIED} DESC"
            )?.apply {

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
        return  mutableListOf<ItemType>().apply {
            temp.asMap().forEach{
                add(PictureHeader(BaseFragment.headerType,it.key))
                addAll(it.value)
            }
        }

    }
}