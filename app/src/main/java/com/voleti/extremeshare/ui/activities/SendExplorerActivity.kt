package com.voleti.extremeshare.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.fragments.pictures.PictureViewPager
import com.voleti.extremeshare.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_send_explorer.*


@AndroidEntryPoint
class  SendExplorerActivity: AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>()

    private val tabNames = arrayOf("Photos","Files","Videos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_explorer)
        setSupportActionBar(toolbar)
        initComponents()
        setupTabLayout()

    }

    private fun initComponents(){
        viewPager.adapter = object :FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return 1
            }
            override fun createFragment(position: Int): Fragment {
                return PictureViewPager()
            }
        }
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }


    private  fun setupTabLayout(){
        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            tab.text = tabNames[position]
            viewPager.setCurrentItem(tab.position,true)

        }.attach()
    }







}