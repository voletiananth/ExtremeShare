package com.voleti.extremeshare.ui.baseUI.config

import androidx.fragment.app.Fragment

abstract class BaseViewPagerConfig {

    abstract fun createFragment(position:Int):Fragment
    val tabNames = mutableListOf<String>()
    val tabIcons = mutableListOf<Int>()

}