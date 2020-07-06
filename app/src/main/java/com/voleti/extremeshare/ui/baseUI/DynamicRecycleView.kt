package com.voleti.extremeshare.ui.baseUI

import androidx.databinding.ViewDataBinding
import com.voleti.extremeshare.ui.baseUI.config.DynamicConfig


class DynamicRecycleView(private val dynamicConfig: DynamicConfig<BaseModelImpl,BaseModelImpl>):BaseFragment(dynamicConfig) {
    companion object{
       const val mainView = 0
       const val subview = 1
    }



    private var currentView = mainView


    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when(currentView){
            mainView -> super.bind(viewBinding, position)
            subview->dynamicConfig.subBind(viewBinding,position)
        }

    }

    override fun itemCount(): Int {
        if(currentView == subview)
            return dynamicConfig.subData.size
        return super.itemCount()
    }

    override fun itemViewType(position: Int): Int {
        if(currentView == subview)
            return itemViewType(position)
        return super.itemViewType(position)
    }

}