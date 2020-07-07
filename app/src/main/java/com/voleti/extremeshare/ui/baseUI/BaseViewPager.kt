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
        viewPager.adapter = object :FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return 1
            }

            override fun createFragment(position: Int): Fragment {
                return config.createFragment(position)
            }


        }

        TabLayoutMediator(tabLayout,viewPager){tab, position ->
                        tab.icon = resources.getDrawable(config.tabIcons[position],null)
                        tab.text = config.tabNames[position]
            viewPager.setCurrentItem(tab.position,true)
        }.attach()



        return inflater.inflate(R.layout.base_viewpager_explorer,container,false)
    }

}