package com.voleti.extremeshare.ui.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.baseUI.config.BaseViewPagerConfig
import kotlinx.android.synthetic.main.base_viewpager_explorer.*

class BaseViewPager(val config: BaseViewPagerConfig):Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.base_viewpager_explorer,container,false)
    }

     override fun onActivityCreated(savedInstanceState: Bundle?) {
         super.onActivityCreated(savedInstanceState)
         viewPager.adapter = object :FragmentStateAdapter(this){
             override fun getItemCount(): Int {
                 return config.itemCount
             }

             override fun createFragment(position: Int): Fragment {
                 return config.createFragment(position){ name,tabPosition ->
                     tabLayout.getTabAt(tabPosition)?.text = name
                 }
             }


         }

         TabLayoutMediator(tabLayout,viewPager){tab, position ->
             tab.icon = resources.getDrawable(config.tabIcons[position],null)
             viewPager.setCurrentItem(tab.position,true)
         }.attach()
     }

}