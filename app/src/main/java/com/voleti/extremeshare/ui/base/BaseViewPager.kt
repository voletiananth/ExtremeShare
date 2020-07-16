package com.voleti.extremeshare.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.voleti.extremeshare.R
import kotlinx.android.synthetic.main.base_viewpager_explorer.*

abstract class BaseViewPager:Fragment( R.layout.base_viewpager_explorer) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       initComponents()
        setupTab()

    }
    abstract val tabIcons:Array<Int>

    private fun initComponents(){
        viewPager.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int = tabIcons.size
            override fun createFragment(position: Int):Fragment = this@BaseViewPager.createFragment(position)

        }
    }

    fun setTabName(index:Int,text:String){
        tabLayout.getTabAt(index)?.text = text
    }

   protected abstract fun createFragment(position: Int):Fragment

    private fun setupTab(){
        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            tab.icon = resources.getDrawable(tabIcons[position],null)
          viewPager.setCurrentItem(tab.position,true)

        }.attach()
    }


}