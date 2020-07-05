package com.voleti.extremeshare.ui.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.baseUI.config.BaseConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseFragment(private val config:BaseConfig<BaseModelImpl>):Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initComponents()
        return inflater.inflate(R.layout.base_fragment,container,false)
    }

    var tabName = 0

    fun getTabName(block:(tabName:String)->Unit){
        loadData()
        block(tabName.toString())
    }

   protected var data = config.mainData

    private fun loadData(){
        config.run { context?.contentResolver?.query(contentUri,projection,selection,selectionArgs,selectOrder) }?.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                if (moveToFirst()){
                    do{
                        config.mainCursor()
                    }while (moveToNext())
                }
            }
        }

    }

    private fun initComponents(){

    }

}