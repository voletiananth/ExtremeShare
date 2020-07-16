package com.voleti.extremeshare.data.pictures

import android.content.ContentResolver
import android.net.Uri
import android.provider.MediaStore
import com.voleti.extremeshare.data.DynamicRepository
import kotlinx.coroutines.withContext

class PictureFolderRepository(contentResolver: ContentResolver):DynamicRepository(contentResolver) {
    override val contentUri: Uri
        get() = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    override val projection: Array<String>?
        get() = arrayOf(MediaStore.Images.Media._ID)


    override suspend fun loadData() {
            withContext(dispatcherIo){

            }
















        super.loadData()
    }


}