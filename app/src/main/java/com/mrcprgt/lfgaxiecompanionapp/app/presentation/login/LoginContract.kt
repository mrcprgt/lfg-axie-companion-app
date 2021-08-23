package com.mrcprgt.lfgaxiecompanionapp.app.presentation.login

import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BaseView

interface LoginContract {
    interface View : BaseView {
        fun closeApp()
        fun navigateToHome()

        fun showErrorRonin(message: String)
        fun showErrorManagerShare(message: String)
        fun showErrorScholarShare(message: String)
        fun showErrorInventorySLP(message: String)

        fun clearErrors()
    }

    interface Presenter : BasePresenter<View> {
        fun onLoginClicked(ronin: String, managerShare: Int?, scholarShare: Int?, inventorySlp: Int?)
    }
}