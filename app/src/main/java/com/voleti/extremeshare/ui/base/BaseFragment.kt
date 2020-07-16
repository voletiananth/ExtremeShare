package com.voleti.extremeshare.ui.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.model.ItemType
import kotlinx.android.synthetic.main.base_fragment.*


abstract class BaseFragment:Fragment(R.layout.base_fragment) {

     companion object {
         const val headerType = 0
         const val contentType = 1
         const val emptyType = 2
     }

    protected  val baseData = mutableListOf<ItemType>()


     abstract fun baseBind(viewBinding: ViewDataBinding,position: Int)
     abstract fun baseViewType(position:Int):Int



     open fun itemCount():Int = baseData.size
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

             layoutManager = baseLayoutManager
         }

     }


    abstract val baseLayoutManager:RecyclerView.LayoutManager


     fun setManagerToDefault(){
         baseRecycleView.layoutManager = baseLayoutManager
     }

    protected fun changeLayoutManager(layoutManager: RecyclerView.LayoutManager){
         baseRecycleView.layoutManager = layoutManager
     }


    protected fun notifyDataChanged(){
        baseRecycleView.adapter?.notifyDataSetChanged()
    }


}