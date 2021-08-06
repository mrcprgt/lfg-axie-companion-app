package com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord

import javax.inject.Inject

class SlpRecordPresenter @Inject constructor(): SlpRecordContract.Presenter {

    private var view:SlpRecordContract.View? = null

    override fun onViewReady(view: SlpRecordContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        this.view = null
    }
    override fun onAddRecordMenuClicked() {
        TODO("Not yet implemented")
    }

    override fun onAddRecordClicked() {
        TODO("Not yet implemented")
    }

    override fun onLoadMore(lastPosition: Int) {
        TODO("Not yet implemented")
    }

    override fun onSyncClicked() {
        TODO("Not yet implemented")
    }

}