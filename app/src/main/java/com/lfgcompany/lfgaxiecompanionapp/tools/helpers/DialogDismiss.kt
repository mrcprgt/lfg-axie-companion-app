package com.lfgcompany.lfgaxiecompanionapp.tools.helpers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

class DialogsDismiss {
    companion object {
        fun dismiss(activity: AppCompatActivity) {
            val fragments = activity.supportFragmentManager.fragments
            fragments.forEach {
                if (it is DialogFragment) {
                    it.dismissAllowingStateLoss()
                }
            }
        }
    }
}
