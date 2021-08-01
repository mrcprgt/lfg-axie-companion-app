package com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base

import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.SimpleLoadingDialog
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.SimpleLoadingMessageDialog
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.showDialogAllowingStateLoss
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    private var simpleLoadingDialog : SimpleLoadingDialog? = null
    private var simpleLoadingMessageDialog: SimpleLoadingMessageDialog? = null

    protected fun showLoadingDialog(title : String?, message : String) {
        if(simpleLoadingMessageDialog == null) {
            simpleLoadingMessageDialog = SimpleLoadingMessageDialog.newInstance(title, message)
            try {
                simpleLoadingMessageDialog!!.show(supportFragmentManager, "loading_with_message_dialog")
            } catch (exception : Exception) {
                simpleLoadingMessageDialog!!.showDialogAllowingStateLoss(supportFragmentManager, "loading_with_message_dialog")
            }
            return
        }
        simpleLoadingMessageDialog!!.showMessage(title, message)
    }

    protected fun hideLoadingDialog() {
        if(simpleLoadingMessageDialog != null) {
            simpleLoadingMessageDialog?.dismissAllowingStateLoss()
        }

        if(simpleLoadingDialog != null)
            simpleLoadingDialog?.dismissAllowingStateLoss()

        simpleLoadingMessageDialog = null
        simpleLoadingDialog = null
    }

    protected fun showLoadingDialogSuccess(title : String?, message: String) {
        if(simpleLoadingMessageDialog != null) {
            simpleLoadingMessageDialog!!.showSuccess(title, message)
            return
        }
    }

    protected fun showLoadingDialog() {
        if(simpleLoadingDialog == null) {
            simpleLoadingDialog = SimpleLoadingDialog()
            try {
                simpleLoadingDialog!!.show(supportFragmentManager, "loading_dialog")
            }catch (exception : Exception) {
                simpleLoadingDialog!!.showDialogAllowingStateLoss(supportFragmentManager, "loading_dialog")
            }
        }
    }
}
