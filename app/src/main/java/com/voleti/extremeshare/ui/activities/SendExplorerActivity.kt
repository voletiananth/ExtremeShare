package com.voleti.extremeshare.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.voleti.extremeshare.R

import kotlinx.android.synthetic.main.activity_send_explorer.*

class SendExplorerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_explorer)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        viewPager.adapter = object :FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return tabNames.size
            }

            override fun createFragment(position: Int): Fragment {
                return Fragment()
            }
        }

        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            tab.text =tabNames[position]
            viewPager.setCurrentItem(tab.position,true)

        }
    }

   private val tabNames = arrayOf("Photos")



}