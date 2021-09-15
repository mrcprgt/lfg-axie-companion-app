package com.lfgcompany.lfgaxiecompanionapp.app.presentation.splash

import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BaseView

interface SplashContract {
    interface View : BaseView {
        fun closeApp()
        fun navigateToHome()
        fun navigateToLogin()
    }

    interface Presenter : BasePresenter<View> {}
}