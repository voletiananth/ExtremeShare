package com.voleti.extremeshare.ui.datamodels

import android.net.Uri
import com.voleti.extremeshare.ui.baseUI.BaseModelImpl

data class PictureContent(override val viewType: Int,val imageUri :Uri) :BaseModelImpl {
}