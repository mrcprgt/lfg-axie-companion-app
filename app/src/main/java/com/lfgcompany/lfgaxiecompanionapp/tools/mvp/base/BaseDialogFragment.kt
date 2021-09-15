package com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.SimpleLoadingDialog
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.SimpleLoadingMessageDialog
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.showDialogAllowingStateLoss
import dagger.android.support.DaggerDialogFragment

abstract class BaseDialogFragment : DaggerDialogFragment() {

    private var simpleLoadingDialog : SimpleLoadingDialog? = null
    private var simpleLoadingMessageDialog: SimpleLoadingMessageDialog? = null

    protected var isUseParentFragmentManagerForDialog = false


    protected fun showLoadingDialog(title : String?, message : String) {
        if(simpleLoadingMessageDialog == null) {
            simpleLoadingMessageDialog = SimpleLoadingMessageDialog.newInstance(title, message)
            try {
                simpleLoadingMessageDialog!!.show(getConfiguredFragmentManager(), "loading_with_message_dialog")
            }catch (exception : Exception) {
                simpleLoadingMessageDialog!!.showDialogAllowingStateLoss(getConfiguredFragmentManager(), "loading_with_message_dialog")
            }
            return
        }
        simpleLoadingMessageDialog!!.showMessage(title, message)
    }

    protected fun showLoadingDialogSuccess(title : String?, message: String) {
        if(simpleLoadingMessageDialog != null) {
            simpleLoadingMessageDialog!!.showSuccess(title, message)
            return
        }
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

    protected fun showLoadingDialog() {
        if(simpleLoadingDialog == null) {
            simpleLoadingDialog = SimpleLoadingDialog()
            try {
                simpleLoadingDialog!!.show(getConfiguredFragmentManager(), "loading_dialog")
            }catch (exception : Exception) {
                simpleLoadingDialog!!.showDialogAllowingStateLoss(getConfiguredFragmentManager(), "loading_dialog")
            }

        }
    }

    protected fun getSupportActionbar() : ActionBar {
        return ((activity) as AppCompatActivity).supportActionBar!!
    }

    protected fun setSupportActionbar(toolbar : Toolbar) {
        ((activity) as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun getConfiguredFragmentManager() : FragmentManager {
        return if(isUseParentFragmentManagerForDialog) parentFragmentManager else childFragmentManager
    }
}
