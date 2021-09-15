package com.lfgcompany.lfgaxiecompanionapp.tools.mvp

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.lfgcompany.lfgaxiecompanionapp.R

class MessageDialog : DialogFragment() {
    private var title: String = ""
    private var message: String = ""

    private var positiveText: String = ""
    private var negativeText: String = ""
    private var isSingleButton = true

    private var positiveClick: ((View) -> Unit?)? = null
    private var negativeClick: ((View) -> Unit?)? = null

    @SuppressLint("InflateParams")
    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater

        val view = if(isSingleButton)
            inflater.inflate(R.layout.single_button_message_dialog, null)
        else
            inflater.inflate(R.layout.dual_button_message_dialog, null)

        view.findViewById<TextView>(R.id.tvTitle).text = title

        view.findViewById<TextView>(R.id.tvMessage).text = message

        val btnConfirm = view.findViewById<TextView>(R.id.btnConfirm)
        btnConfirm.text = positiveText
        btnConfirm.setOnClickListener {
            doConfirmClick(it)
        }

        if(!isSingleButton) {
            val btnCancel = view.findViewById<TextView>(R.id.btnCancel)
            btnCancel.text = negativeText
            btnCancel.setOnClickListener {
                doCancelClick(it)
            }
        }

        val dialog = AlertDialog.Builder(requireActivity(), R.style.LFG_AlertDialog)
        dialog.setView(view)
        dialog.setCancelable(false)


        return dialog.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return view
    }

    fun title(title: String): MessageDialog {
        this.title = title
        return this
    }

    fun message(message: String): MessageDialog {
        this.message = message
        return this
    }

    fun cancelable(isCancelable: Boolean): MessageDialog {
        setCancelable(isCancelable)
        return this
    }

    fun positiveButton(text: String, onClick: (View) -> Unit?): MessageDialog {
        positiveText = text
        positiveClick = onClick
        return this
    }

    fun negativeButton(text: String, onClick: (View) -> Unit?): MessageDialog {
        negativeText = text
        negativeClick = onClick

        isSingleButton = false
        return this
    }

    private fun doConfirmClick(view: View) {
        dismiss()
        positiveClick?.invoke(view)
    }
    private fun doCancelClick(view: View) {
        dismiss()
        negativeClick?.invoke(view)
    }
}
