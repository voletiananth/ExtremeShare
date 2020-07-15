package com.voleti.extremeshare.ui.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.voleti.extremeshare.R
import kotlinx.android.synthetic.main.base_fragment.*


abstract class BaseFragment:Fragment(R.layout.base_fragment) {

     companion object {
         const val headerType = 0
         const val contentType = 1
     }


     abstract fun baseItemCount():Int
     abstract fun baseBind(viewBinding: ViewDataBinding,position: Int)
     abstract fun baseViewType(position:Int):Int



     open fun itemCount():Int = baseItemCount()
     open fun bind(viewBinding: ViewDataBinding,position: Int){
         baseBind(viewBinding, position)
     }
     open fun itemViewType(position:Int):Int = baseViewType(position)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
                initComponents()
    }

     private fun initComponents(){
         class  MyHolder(val viewDataBinding: ViewDataBinding):RecyclerView.ViewHolder(viewDataBinding.root)
         baseRecycleView.apply {
             adapter = object :RecyclerView.Adapter<MyHolder>(){
                 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
                     MyHolder(DataBindingUtil.inflate(
                         layoutInflater,
                         viewType,
                         parent,
                         false))

                 override fun getItemCount(): Int = this@BaseFragment.itemCount()

                 override fun onBindViewHolder(holder: MyHolder, position: Int) {
                     bind(holder.viewDataBinding,position)
                 }

                 override fun getItemViewType(position: Int) = this@BaseFragment.itemViewType(position)

             }

             layoutManager = mainLayoutManager
         }

     }


    abstract val mainLayoutManager:RecyclerView.LayoutManager


     fun setManagerToDefault(){
         baseRecycleView.layoutManager = mainLayoutManager
     }

    protected fun changeLayoutManager(layoutManager: RecyclerView.LayoutManager){
         baseRecycleView.layoutManager = layoutManager
     }



     override fun onDestroy() {
         super.onDestroy()
         baseRecycleView.adapter = null
     }

    protected fun notifyDataChanged(){
        baseRecycleView.adapter?.notifyDataSetChanged()
    }


}