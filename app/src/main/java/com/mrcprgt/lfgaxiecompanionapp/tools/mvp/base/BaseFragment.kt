package com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base

import android.os.Bundle
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.SimpleLoadingDialog
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.SimpleLoadingMessageDialog
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.showDialogAllowingStateLoss
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment {

    constructor(@LayoutRes contentLayoutId: Int) : super()

    constructor() : super()

    private var simpleLoadingDialog: SimpleLoadingDialog? = null
    private var simpleLoadingMessageDialog: SimpleLoadingMessageDialog? = null

    private var originalMode: Int? = null

    protected var isUseParentFragmentManagerForDialog = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        originalMode = activity?.window?.getSoftInputMode()
    }

    override fun onDestroyView() {
        originalMode?.let { activity?.window?.setSoftInputMode(it) }
        super.onDestroyView()
    }

    protected fun showLoadingDialog(title: String?, message: String) {
        if (simpleLoadingMessageDialog == null) {
            simpleLoadingMessageDialog = SimpleLoadingMessageDialog.newInstance(title, message)
            try {
                simpleLoadingMessageDialog!!.show(
                    getConfiguredFragmentManager(),
                    "loading_with_message_dialog"
                )
            } catch (exception: Exception) {
                simpleLoadingMessageDialog!!.showDialogAllowingStateLoss(
                    getConfiguredFragmentManager(),
                    "loading_with_message_dialog"
                )
            }
            return
        }
        simpleLoadingMessageDialog!!.showMessage(title, message)
    }

    protected fun showLoadingDialogSuccess(title: String?, message: String) {
        if (simpleLoadingMessageDialog != null) {
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
            } catch (exception: Exception) {
                simpleLoadingDialog!!.showDialogAllowingStateLoss(
                    getConfiguredFragmentManager(),
                    "loading_dialog"
                )
            }
        }
    }

    protected fun getSupportActionbar() : ActionBar {
        return ((activity) as AppCompatActivity).supportActionBar!!
    }

    protected fun setSupportActionbar(toolbar: Toolbar) {
        ((activity) as AppCompatActivity).setSupportActionBar(toolbar)
    }

    private fun getConfiguredFragmentManager() : FragmentManager {
        return if(isUseParentFragmentManagerForDialog) parentFragmentManager else childFragmentManager
    }

    fun Window.getSoftInputMode() : Int {
        return attributes.softInputMode
    }
}