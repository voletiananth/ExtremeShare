package com.voleti.extremeshare.utils

import androidx.fragment.app.Fragment
import com.voleti.extremeshare.R


fun Fragment.isSmall() = getBool(R.bool.screen_size_isSmall)
fun Fragment.isNormal() = getBool(R.bool.screen_size_isNormal)
fun Fragment.isLarge() = getBool(R.bool.screen_size_isLarge)
fun Fragment.isXLarge() = getBool(R.bool.screen_size_isXLarge)
fun Fragment.isLandscape() = getBool(R.bool.screen_isLandscape)
private fun Fragment.getBool(id:Int) = resources.getBoolean(id)