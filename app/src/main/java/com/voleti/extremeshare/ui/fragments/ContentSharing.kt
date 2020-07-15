package com.voleti.extremeshare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.voleti.extremeshare.R
import kotlinx.android.synthetic.main.fragment_content_sharing.view.*

class ContentSharing: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_sharing,container,false).apply {
            btnSend.setOnClickListener {
                findNavController().navigate(R.id.action_contentSharing_to_sendExplorerActivity)
            }
        }
    }




}