package com.voleti.extremeshare.ui.baseUI.config

import androidx.fragment.app.Fragment

abstract class BaseViewPagerConfig {

    abstract fun createFragment(position:Int,tab:(name:String,tabPosition:Int)->Unit):Fragment
     abstract val itemCount:Int
   abstract val tabIcons:Array<Int>

}