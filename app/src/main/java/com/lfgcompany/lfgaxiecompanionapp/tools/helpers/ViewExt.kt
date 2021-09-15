package com.lfgcompany.lfgaxiecompanionapp.tools.helpers

import android.view.View

fun View.setOnClickWithDelay(onClicked: () -> Unit) {
    setOnClickListener(
        object : LockedOnClickListener() {
            override fun onSingleClicked(view: View) {
                onClicked.invoke()
            }
        }
    )
}