package com.mrcprgt.lfgaxiecompanionapp.tools.mvp

interface BasePresenter<T : BaseView> {
    fun onViewReady(view: T)
    fun onViewDetach()
}
