package com.mrcprgt.lfgaxiecompanionapp.tools.helpers

import android.view.View

abstract class LockedOnClickListener : View.OnClickListener {

    private var lastClickTime = System.currentTimeMillis()

    companion object {
        private const val CLICK_TIME_INTERVAL: Long = 500
    }

    abstract fun onSingleClicked(view: View)

    override fun onClick(p0: View?) {
        val now = System.currentTimeMillis()
        if (now - lastClickTime >= CLICK_TIME_INTERVAL) {
            onSingleClicked(p0!!)
            lastClickTime = now
        }
    }
}
