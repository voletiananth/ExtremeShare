package com.voleti.extremeshare.utils

import android.Manifest

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.voleti.extremeshare.R

import com.voleti.extremeshare.dialog.RationalePermissionRequest.PermissionRequest


object AppUtils {




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