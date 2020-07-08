package com.voleti.extremeshare.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.voleti.extremeshare.R
import com.voleti.extremeshare.callbacks.PictureViewPager
import com.voleti.extremeshare.ui.baseUI.BaseViewPager
import kotlinx.android.synthetic.main.activity_send_explorer.*

class SendExplorerActivity: AppCompatActivity(R.layout.activity_send_explorer) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager.adapter = object :FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return tabNames.size
            }

            override fun createFragment(position: Int): Fragment {
                return BaseViewPager(PictureViewPager())
            }
        }

        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            tab.text =tabNames[position]
            viewPager.setCurrentItem(tab.position,true)

        }
    }

   private val tabNames = arrayOf("Photos")



}