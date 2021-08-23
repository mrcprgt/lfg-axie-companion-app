package com.mrcprgt.lfgaxiecompanionapp.tools.helpers

import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar

fun View.setOnClickWithDelay(onClicked: () -> Unit) {
    setOnClickListener(
        object : LockedOnClickListener() {
            override fun onSingleClicked(view: View) {
                onClicked.invoke()
            }
        }
    )
}