package com.voleti.extremeshare.ui.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.baseUI.config.BaseConfig
import kotlinx.android.synthetic.main.base_fragment.*
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



    private fun loadData(){
        config.run { context?.contentResolver?.query(contentUri,projection,selection,selectionArgs,selectOrder) }?.apply {
            tabName = count
            lifecycleScope.launch(Dispatchers.IO) {
                if (moveToFirst()){
                    do{
                        config.loadCursor(this,this@apply)
                    }while (moveToNext())
                }
            }
        }

    }

    private fun initComponents(){

        baseRecycleView.layoutManager = config.mainLayoutManager(context)
         class MyHolder(val viewBinding: ViewDataBinding):RecyclerView.ViewHolder(viewBinding.root)
        
        baseRecycleView.adapter = object : RecyclerView.Adapter<MyHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder = MyHolder(
                DataBindingUtil.inflate(LayoutInflater.from(context),
                    viewType,
                    parent,
                    false)
            )



            override fun getItemCount(): Int = this@BaseFragment.itemCount()

            override fun onBindViewHolder(holder: MyHolder, position: Int) {
               bind(holder.viewBinding,position)
            }

            override fun getItemViewType(position: Int): Int {
                return itemViewType(position)
            }


        }

    }
    open fun itemCount() = config.mainData.size

    open fun bind(viewBinding: ViewDataBinding,position: Int)  = config.mainBind(viewBinding, position)


    open fun itemViewType(position:Int):Int = config.mainViewType(position)

    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager){
        baseRecycleView.layoutManager = layoutManager
        baseRecycleView.adapter?.notifyDataSetChanged()
    }

}