package com.mrcprgt.lfgaxiecompanionapp.app.presentation.stats

import javax.inject.Inject

class StatsPresenter @Inject constructor() : StatsContract.Presenter {

    private var view: StatsContract.View? = null

    override fun onViewReady(view: StatsContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        this.view = null
    }

    override fun onRefreshClicked() {
        TODO("Not yet implemented")
    }

}