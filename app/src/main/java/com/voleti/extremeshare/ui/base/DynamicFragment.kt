package com.voleti.extremeshare.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.voleti.extremeshare.ui.model.ItemType
import kotlinx.android.synthetic.main.base_fragment.*

abstract class DynamicFragment : BaseFragment() {
    companion object{
        const val mainView = headerType+1
        const val dynamicView = contentType+1
    }

    private var currentView = mainView


    fun changeCurrentView(position: Int){
        when(currentView){
            mainView->{
                currentView = dynamicView
                changeLayoutManager(dynamicLayoutManager)

            }
            dynamicView->{
                currentView = mainView
                setManagerToDefault()

            }
        }
        notifyDataChanged()
        baseRecycleView.scrollToPosition(position)
    }

    protected  val dynamicData = mutableListOf<ItemType>()


    abstract fun dynamicBind(viewBinding: ViewDataBinding,position: Int)
    abstract fun dynamicViewType(position:Int):Int

    abstract val dynamicLayoutManager:RecyclerView.LayoutManager

    override fun itemCount(): Int {
        if(currentView == dynamicView)
            return dynamicData.size
        return super.itemCount()
    }

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when(currentView){
            mainView -> super.bind(viewBinding, position)
            dynamicView->dynamicBind(viewBinding,position)
        }
    }

    override fun itemViewType(position: Int): Int {
        if(currentView == dynamicView)
            return dynamicViewType(position)
        return super.itemViewType(position)
    }



}