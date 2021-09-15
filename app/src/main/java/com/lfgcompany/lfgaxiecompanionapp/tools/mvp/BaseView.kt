package com.lfgcompany.lfgaxiecompanionapp.tools.mvp



interface BaseView {
    fun hideProgressDialog()

    fun showProgressDialog()

    fun showProgressDialog(message: String)

    fun showProgressDialog(title: String? = "Please wait", message: String)

    fun showToast(message: String)

    fun showErrorDialog(
        title: String,
        message: String,
        onOkClicked: () -> Unit,
        onDeclineClicked: () -> Unit
    )

    fun showMessageDialog(
        title: String,
        message: String,
        onOkClicked: () -> Unit
    )
}
