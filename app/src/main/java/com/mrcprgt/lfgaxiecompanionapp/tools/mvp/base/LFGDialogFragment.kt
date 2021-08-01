package com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base

import android.widget.Toast
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.showAllowingStateLoss
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BaseView
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.MessageDialog

abstract class LFGDialogFragment : BaseDialogFragment(), BaseView {

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
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
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
            .positiveButton("OK") {
                onOkClicked.invoke()
            }
            .negativeButton("Cancel") {
                onDeclineClicked.invoke()
            }
            .cancelable(false)
            .showAllowingStateLoss(parentFragmentManager, "error_dialog")
    }
}
