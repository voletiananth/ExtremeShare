package com.voleti.extremeshare.data

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.voleti.extremeshare.ui.model.ItemType

abstract class BaseRepository(protected val contentResolver: ContentResolver) {
    private val _tabName = MutableLiveData(0)
    val tableName: LiveData<Int> = _tabName


    private val _mainData = MutableLiveData<List<ItemType>>()
    val mainData: LiveData<List<ItemType>> = _mainData

    fun submitTableName(name: Int) {
        _tabName.value = name
    }

    fun submitMainData(list: List<ItemType>) {
        _mainData.value = list
    }

    abstract fun loadData()
}