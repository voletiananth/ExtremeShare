package com.voleti.extremeshare.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.baseUI.DynamicRecycleView
import kotlinx.android.synthetic.main.activity_send_explorer.*

class SendExplorerActivity: AppCompatActivity(R.layout.activity_send_explorer) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager.adapter = object :FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return 5
            }

            override fun createFragment(position: Int): Fragment {
                return Fragment()
            }
        }
    }
}