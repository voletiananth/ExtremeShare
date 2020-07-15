package com.voleti.extremeshare.data

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.voleti.extremeshare.ui.model.ItemType

abstract class DynamicRepository(contentResolver: ContentResolver):BaseRepository(contentResolver) {

    private val _dynamicData = MutableLiveData<List<ItemType>>()
    val dynamicData: LiveData<List<ItemType>> = _dynamicData


    fun submitDynamicData(list:List<ItemType>){
        _dynamicData.value = list
    }
}