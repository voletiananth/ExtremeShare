package com.voleti.extremeshare.ui.baseUI.config

import com.voleti.extremeshare.ui.baseUI.BaseModelImpl

abstract class DynamicConfig<D:BaseModelImpl,B:BaseModelImpl>:BaseConfig<B>(){
    val subdata = mutableListOf<D>()

    fun  offerSubData(item:D){
        subdata.add(item)
    }
}