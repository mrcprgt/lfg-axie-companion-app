package com.lfgcompany.lfgaxiecompanionapp.tools.mvp

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

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
