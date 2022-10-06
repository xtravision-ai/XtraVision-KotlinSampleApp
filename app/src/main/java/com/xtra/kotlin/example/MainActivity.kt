package com.xtra.kotlin.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//import ai.xtravision.*
////import ai.xtravision.base.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        val connectionData = ConnectionData(authToken, assessmentName)
//        val reqData = RequestData(isPreJoin)
//        val libData = LibData(
//            this,
//            previewView,
//            this,
//            selectedCamera
//        )
//
//        // Check and Ask Camera permission
//        PermissionHelper.checkAndRequestCameraPermissions(this)
//
//        // if permission is granted, then let's start assessment
//        if (PermissionHelper.cameraPermissionsGranted(this)) {
//            val xtraVisionAIManger = XtraVisionAssessment(connectionData, reqData, libData)
//            xtraVisionAIManger!!.initiate(this)
//        } else {
//            PermissionHelper.checkAndRequestCameraPermissions(this)
//        }
    }
}