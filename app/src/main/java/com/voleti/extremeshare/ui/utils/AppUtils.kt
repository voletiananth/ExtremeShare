package com.voleti.extremeshare.ui.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.dialog.RationalePermissionRequest.PermissionRequest
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


object AppUtils {

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