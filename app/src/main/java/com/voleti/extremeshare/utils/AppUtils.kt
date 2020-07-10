package com.voleti.extremeshare.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.voleti.extremeshare.R
import com.voleti.extremeshare.dialog.RationalePermissionRequest.PermissionRequest
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


object AppUtils {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun attachImage(view:ImageView,url:Uri){
           Glide.with(view.context).load(url).override(200).centerCrop().into(view)
    }



    fun formatToDate(seconds:Long):String {

        return SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(seconds*1000L)).run {
            val year = Calendar.getInstance().get(Calendar.YEAR).toString().trim()
            if (contains(year))
                return@run  replace(", $year","")

          this
        }

    }


    fun checkRunningCondition(context: Context):Boolean = getRequiredPermissions(context).run {
        forEach{
           if (ActivityCompat.checkSelfPermission(context,it.permission)!=PackageManager.PERMISSION_GRANTED)
             return@run  false
        }
        true
    }


    fun getRequiredPermissions(context: Context): List<PermissionRequest> = ArrayList<PermissionRequest>().apply {
            add(PermissionRequest(context,Manifest.permission.WRITE_EXTERNAL_STORAGE, R.string.requestPermissionStorage,R.string.requestPermissionStorageSummary))

        }







}