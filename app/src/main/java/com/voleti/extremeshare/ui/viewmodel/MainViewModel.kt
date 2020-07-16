package com.voleti.extremeshare.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voleti.extremeshare.data.MediaRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(val mediaRepository: MediaRepository): ViewModel() {


    init {
        viewModelScope.apply {
            launch { mediaRepository.pictureGrid.loadData() }
        }
    }


}