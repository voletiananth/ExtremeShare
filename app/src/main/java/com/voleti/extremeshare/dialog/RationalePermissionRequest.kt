package com.voleti.extremeshare.dialog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.activities.MainActivity.Companion.requestPermissionAll

class RationalePermissionRequest
                    private constructor(private val activity: Activity,private val permissionRequest: PermissionRequest,
                                        private val killActivityOtherwise:Boolean) : AlertDialog.Builder(activity) {


    companion object{
        fun requestIfNecessary(activity: Activity,permissionRequest: PermissionRequest,killActivityOtherwise:Boolean) :AlertDialog? =
            if (ActivityCompat.checkSelfPermission(activity, permissionRequest.permission) == PackageManager.PERMISSION_GRANTED)
                null
            else
              RationalePermissionRequest(activity,permissionRequest, killActivityOtherwise).show()
    }

    init {
        setCancelable(false)
        setTitle(permissionRequest.title)
        setMessage(permissionRequest.message)

        if(ActivityCompat.shouldShowRequestPermissionRationale(activity,permissionRequest.permission))
            setNeutralButton(R.string.btn_setting) { _, _ ->
                activity.startActivity(Intent().apply {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.fromParts("package",activity.packageName,null)
                })
            }
            setPositiveButton(R.string.btn_ask){ _,_->
                ActivityCompat.requestPermissions(activity, arrayOf(permissionRequest.permission),
                    requestPermissionAll)
            }

            setNegativeButton(R.string.btn_reject){_,_ ->
                    if (killActivityOtherwise){
                        activity.finish()
                    }
                }



    }


     class PermissionRequest @JvmOverloads constructor(
         val permission: String,
         val title: String,
         val message: String,
         val required: Boolean = true
    ) {
        constructor(
            context: Context,
            permission: String,
            titleRes: Int,
            messageRes: Int
        ) : this(permission, context.getString(titleRes), context.getString(messageRes))


    }
}