package com.xtra.kotlin.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import ai.xtravision.*
import ai.xtravision.util.*
import androidx.camera.view.PreviewView

import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity(), XtraVisionAIListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // STEP-1:: Set Required Variable
        val authToken = "__AUTH_TOKEN__"
        val assessmentName = "SQUATS"
        val selectedCamera = 0  //0: Front Camera, 1: Back Camera


        val isPreJoin = false
        val previewView : PreviewView = findViewById<PreviewView>(R.id.xtraVisionPreviewView)

        // STEP-2:: create initial Objects: connectionData,requestData and libData

        val assessmentConfig = XtraVisionAssessmentConfig(
            repsThreshold = 5,
            graceTimeThreshold = 20)

        val connectionData = XtraVisionConnectionData(
            authToken, assessmentName,
            assessmentConfig = assessmentConfig
        )
        val reqData = XtraVisionRequestData(isPreJoin)
        val libData = XtraVisionLibData(
            this,
            previewView,
            this,
            selectedCamera,
            enableSkeletonView = true
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
        handleResponse(resp)
    }
    private fun handleResponse(resp: String?) {
        try{
            val string = resp.toString()

            // Just pathing to print logs on screen
            this@MainActivity.runOnUiThread(java.lang.Runnable {
                val textView: TextView = findViewById(R.id.xtra_server_response)
                textView.text = string ;
            })

        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }
    /**
     * ==============================
     * Response Listener: End
     * ==============================
     */
}
