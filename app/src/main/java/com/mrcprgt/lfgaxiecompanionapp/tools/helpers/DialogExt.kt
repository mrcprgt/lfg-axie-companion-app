package com.mrcprgt.lfgaxiecompanionapp.tools.helpers

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.MessageDialog
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.SimpleLoadingDialog
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.SimpleLoadingMessageDialog

fun DialogFragment.showAllowingStateLoss(fragmentManager: FragmentManager, tag: String) {
    try {
        val ft = fragmentManager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    } catch (_: IllegalStateException) {
    }
}

fun MessageDialog.showDialogAllowingStateLoss(
    fragmentManager: FragmentManager,
    tag: String
) {
    val ft: FragmentTransaction = fragmentManager.beginTransaction()
    ft.add(this, tag)
    ft.commitAllowingStateLoss()
}

fun SimpleLoadingDialog.showDialogAllowingStateLoss(
    fragmentManager: FragmentManager,
    tag: String
) {
    val ft: FragmentTransaction = fragmentManager.beginTransaction()
    ft.add(this, tag)
    ft.commitAllowingStateLoss()
}


fun SimpleLoadingMessageDialog.showDialogAllowingStateLoss(
    fragmentManager: FragmentManager,
    tag: String
) {
    val ft: FragmentTransaction = fragmentManager.beginTransaction()
    ft.add(this, tag)
    ft.commitAllowingStateLoss()
}

fun DialogFragment.showDialogAllowingStateLoss(
    fragmentManager: FragmentManager,
    tag: String
) {
    val ft: FragmentTransaction = fragmentManager.beginTransaction()
    ft.add(this, tag)
    ft.commitAllowingStateLoss()
}