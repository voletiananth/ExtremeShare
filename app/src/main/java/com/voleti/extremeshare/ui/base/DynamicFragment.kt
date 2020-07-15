package com.voleti.extremeshare.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DynamicFragment : BaseFragment() {
    companion object{
        const val mainView = headerType+1
        const val subView = contentType+1
    }

    private var currentView = mainView

    abstract fun dynamicItemCount():Int
    abstract fun dynamicBind(viewBinding: ViewDataBinding,position: Int)
    abstract fun dynamicViewType(position:Int):Int

    abstract val dynamicLayoutManager:RecyclerView.LayoutManager

    override fun itemCount(): Int {
        if(currentView == subView)
            return dynamicItemCount()
        return super.itemCount()
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when(currentView){
            mainView -> super.bind(viewBinding, position)
            subView->dynamicBind(viewBinding,position)
        }
    }

    override fun itemViewType(position: Int): Int {
        if(currentView == subView)
            return dynamicViewType(position)
        return super.itemViewType(position)
    }


    fun setManagerToDynamic(){
        changeLayoutManager(dynamicLayoutManager)
    }

}