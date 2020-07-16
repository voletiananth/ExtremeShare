package com.voleti.extremeshare.data

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.voleti.extremeshare.ui.model.ItemType
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository(private val contentResolver: ContentResolver) {

    protected abstract val contentUri:Uri
    protected open val projection:Array<String>? = null
    protected open val selection:String? = null
    protected open val selectionArgs:Array<String>? = null
    protected open val sortOrder:String? = null

    protected fun query() = contentResolver.query(contentUri,projection,selection,selectionArgs, sortOrder)

    protected val dispatcherIo = Dispatchers.IO
    protected val dispatcherDefault = Dispatchers.Default

    private val _tabName = MutableLiveData(0)
    val tableName: LiveData<Int> = _tabName


    private val _baseData = MutableLiveData<List<ItemType>>()
    val baseData: LiveData<List<ItemType>> = _baseData


   protected val tempBaseData = mutableListOf<ItemType>()

    fun submitTableName(name: Int) {
        _tabName.value = name
    }

    open suspend fun loadData(){
        _baseData.value = tempBaseData

    }
}