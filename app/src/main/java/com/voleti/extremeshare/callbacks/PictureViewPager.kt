package com.voleti.extremeshare.callbacks

import androidx.fragment.app.Fragment
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.baseUI.DynamicRecycleView
import com.voleti.extremeshare.ui.baseUI.config.BaseViewPagerConfig

class PictureViewPager:BaseViewPagerConfig(){
    override fun createFragment(position: Int,tab:(name:String,tabPosition:Int)->Unit): Fragment {
        tab("1",0)
        return DynamicRecycleView(PictureGrid(){

        })
    }



    override val itemCount: Int
        get() = 1

    override val tabIcons: Array<Int>
        get() = arrayOf(R.drawable.ic_picture_grid)

}