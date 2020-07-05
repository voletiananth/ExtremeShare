package com.voleti.extremeshare.ui.baseUI

import com.voleti.extremeshare.ui.baseUI.config.DynamicConfig


class DynamicRecycleView(private val dynamicConfig: DynamicConfig<BaseModelImpl,BaseModelImpl>):BaseFragment(dynamicConfig) {
    companion object{
       const val mainView = 0
       const val subview = 1
    }

}