package com.lfgcompany.lfgaxiecompanionapp.app.presentation.home

import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.BaseView

interface HomeContract {
    interface View: BaseView{

    }

    interface Presenter: BasePresenter<View>{
        fun onBackPressed()

    }

}