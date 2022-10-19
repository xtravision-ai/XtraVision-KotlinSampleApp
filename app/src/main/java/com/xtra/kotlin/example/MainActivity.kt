package com.xtra.kotlin.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import ai.xtravision.*
import ai.xtravision.util.*
import androidx.camera.view.PreviewView

import android.util.Log

class MainActivity : AppCompatActivity(), XtraVisionAIListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // STEP-1:: Set Required Variable
        val authToken = "_AUTH-TOKEN_"
        val assessmentName = "BANDED_EXTERNAL_ROTATION"
        val isPreJoin = false
        val previewView : PreviewView = findViewById<PreviewView>(R.id.xtraVisionPreviewView)
        val selectedCamera = 1  //0: Front Camera, 1: Back Camera

        // STEP-2:: create initial Objects: connectionData,requestData and libData
        val connectionData = XtraVisionConnectionData(authToken, assessmentName)
        val reqData = XtraVisionRequestData(isPreJoin)
        val libData = XtraVisionLibData(
            this,
            previewView,
            this,
            selectedCamera
        )

        // Check and Ask for Camera permission
        XtraVisionPermissionHelper.checkAndRequestCameraPermissions(this)

        // STEP-3:: if permission is granted, then let's start an assessment
        if (XtraVisionPermissionHelper.cameraPermissionsGranted(this)) {
            // create an object and initiate the Assessment process
            val xtraVisionAIManger = XtraVisionAIManager(
                connectionData, reqData, libData)
            xtraVisionAIManger!!.initiate()
        } else {
            XtraVisionPermissionHelper.checkAndRequestCameraPermissions(this)
        }

    }


    /**
     * ==============================
     * Response Listener: Start
     * ==============================
     */
    override fun onConnectSuccess() {
        Log.d("CustomAIListener","onConnectSuccess")
    }

    override fun onConnectFailed() {
        Log.e("CustomAIListener","onConnectFailed")
    }

    override fun onConnectClose() {
        Log.d("CustomAIListener","onConnectClose")
    }

    override fun onResponse(resp: String?) {
        Log.d("CustomAIListener", resp.toString())
    }
    /**
     * ==============================
     * Response Listener: End
     * ==============================
     */
}
