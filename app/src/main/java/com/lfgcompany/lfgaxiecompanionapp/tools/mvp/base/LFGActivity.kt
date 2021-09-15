package com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base

import android.widget.Toast
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BaseView
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.MessageDialog
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.showDialogAllowingStateLoss

abstract class LFGActivity : BaseActivity(), BaseView {
    override fun showProgressDialog() {
        showLoadingDialog()
    }

    override fun showProgressDialog(message: String) {
        showLoadingDialog(null, message)
    }

    override fun showProgressDialog(title: String?, message: String) {
        showLoadingDialog(title, message)
    }

    override fun hideProgressDialog() {
        hideLoadingDialog()
    }

    override fun showToast(message: String) {
        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun showErrorDialog(
        title: String,
        message: String,
        onOkClicked: () -> Unit,
        onDeclineClicked: () -> Unit
    ) {
        MessageDialog()
            .title(title)
            .message(message)
            .positiveButton("Retry") {
                onOkClicked.invoke()
            }
            .negativeButton("Cancel") {
                onDeclineClicked.invoke()
            }
            .cancelable(false)
            .showDialogAllowingStateLoss(supportFragmentManager, "error_dialog")
    }

    override fun showMessageDialog(title: String, message: String, onOkClicked: () -> Unit) {
        MessageDialog()
            .title(title)
            .message(message)
            .positiveButton("Okay") {
                onOkClicked.invoke()
            }
            .cancelable(false)
            .showDialogAllowingStateLoss(supportFragmentManager, "msg_dialog")
    }
}