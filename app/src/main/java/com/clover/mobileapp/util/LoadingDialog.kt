package com.clover.mobileapp.util

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.clover.mobileapp.R

/*
* Class to show/hide progress dialog
* */
class LoadingDialog (private val mActivity: Activity) {

    private lateinit var isdialog: AlertDialog

    /*
    * Function to show progress dialog
    * */
    fun showProgressDialog(){
        /**set View*/
        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_item,null)
        /**set Dialog*/
        val bulider = AlertDialog.Builder(mActivity)
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog = bulider.create()
        isdialog.show()
    }

    fun hideProgressdialog(){
        if(this::isdialog.isInitialized)
            isdialog.dismiss()
    }
}