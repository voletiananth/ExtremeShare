package com.voleti.extremeshare.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.voleti.extremeshare.R
import com.voleti.extremeshare.ui.dialog.RationalePermissionRequest
import com.voleti.extremeshare.ui.utils.AppUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  var mAlertDialog: AlertDialog?= null
        companion object{
            const val requestPermissionAll  = 100
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        if (!AppUtils.checkRunningCondition(this)){
            requestRequiredPermissions(true)
        }

    }


    private fun requestRequiredPermissions(killActivityOtherwise:Boolean):Boolean{
        if(mAlertDialog!= null && mAlertDialog!!.isShowing){
                return false
        }
        AppUtils.getRequiredPermissions(this).forEach {
            mAlertDialog = RationalePermissionRequest.requestIfNecessary(this,it,killActivityOtherwise)?.apply {
                return true
            }
        }
        return true
        }

    }
