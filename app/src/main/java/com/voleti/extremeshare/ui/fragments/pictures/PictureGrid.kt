package com.voleti.extremeshare.ui.fragments.pictures

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voleti.extremeshare.ui.base.DynamicFragment

class PictureGrid:DynamicFragment() {

    override fun dynamicItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun dynamicBind(viewBinding: ViewDataBinding, position: Int) {
        TODO("Not yet implemented")
    }

    override fun dynamicViewType(position: Int): Int {
        TODO("Not yet implemented")
    }

    override val dynamicLayoutManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(requireContext())

    override fun baseItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun baseBind(viewBinding: ViewDataBinding, position: Int) {
        TODO("Not yet implemented")
    }

    override fun baseViewType(position: Int): Int {
        TODO("Not yet implemented")
    }

    override val mainLayoutManager: RecyclerView.LayoutManager
        get() = GridLayoutManager(requireContext(),3).apply {
            spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    TODO("Not yet implemented")
                }

            }
        }
}