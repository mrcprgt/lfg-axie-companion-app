package com.mrcprgt.lfgaxiecompanionapp.app.presentation.home

import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BasePresenter
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.BaseView

interface HomeContract {
    interface View: BaseView{

    }

    interface Presenter: BasePresenter<View>{
        fun onBackPressed()

    }

}