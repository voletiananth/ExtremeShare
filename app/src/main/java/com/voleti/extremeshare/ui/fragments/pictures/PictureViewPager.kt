package com.voleti.extremeshare.ui.fragments.pictures
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.base.BaseViewPager
import com.voleti.extremeshare.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureViewPager:BaseViewPager() {
   private val viewModel by activityViewModels<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.mediaRepository.pictureGrid.tableName.observe(viewLifecycleOwner) {
            setTabName(0, it.toString())

        }
    }


    override val tabIcons: Array<Int>
        get() = arrayOf(R.drawable.ic_picture_grid)

    override fun createFragment(position: Int): Fragment = PictureGrid()
}