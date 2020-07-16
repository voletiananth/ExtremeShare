package com.voleti.extremeshare.data

import android.content.ContentResolver
import com.voleti.extremeshare.data.pictures.PictureGridRepository
import javax.inject.Inject


class MediaRepository @Inject constructor(contentResolver: ContentResolver) {
    val pictureGrid = PictureGridRepository(contentResolver)
}