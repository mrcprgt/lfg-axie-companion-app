package com.mrcprgt.lfgaxiecompanionapp.tools.mvp

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mrcprgt.lfgaxiecompanionapp.R


class SimpleLoadingMessageDialog : DialogFragment() {
    companion object {

        const val KEY_DIALOG_TITLE = "key_dialog_title"
        const val KEY_DIALOG_MESSAGE = "key_dialog_message"

        @JvmStatic
        fun newInstance(title: String?, message: String): SimpleLoadingMessageDialog {
            val fragment = SimpleLoadingMessageDialog()
            val bundle = Bundle()
            bundle.putString(KEY_DIALOG_TITLE, title)
            bundle.putString(KEY_DIALOG_MESSAGE, message)
            fragment.arguments = bundle
            return fragment
        }
    }

    @SuppressLint("InflateParams")
    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.loading_with_message, null)

        isCancelable = false

        val dialog = AlertDialog.Builder(requireActivity(), R.style.LFG_AlertDialog)
        dialog.setView(view)
        dialog.setCancelable(false)

        var tvProgressTitle: AppCompatTextView
        var tvProgressMessage: AppCompatTextView
        var progressbar: ProgressBar

        with(view) {
            tvProgressTitle = findViewById(R.id.tvProgressTitle)
            tvProgressMessage = findViewById(R.id.tvProgressMessage)
            progressbar = findViewById(R.id.progressBar)
        }

        val attrs = intArrayOf(R.attr.colorPrimary, R.color.white)
        val typedValue = TypedValue()
        val typedArray = requireContext().obtainStyledAttributes(typedValue.data, attrs)
        val progressColor = typedArray.getColor(0, 0)
        typedArray.recycle()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            progressbar.indeterminateDrawable.colorFilter =
                BlendModeColorFilter(progressColor, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            progressbar.indeterminateDrawable.setColorFilter(progressColor, PorterDuff.Mode.SRC_IN)
        }

        arguments?.let {
            val title = it.getString(KEY_DIALOG_TITLE)
            val message = it.getString(KEY_DIALOG_MESSAGE)

            tvProgressTitle.text = if (title.isNullOrEmpty()) "Please wait" else title
            tvProgressMessage.text = message

        } ?: throw IllegalStateException("Please pass a valid argument")

        return dialog.create()
    }

    private fun getProgressTitle(): AppCompatTextView? {
        return dialog?.findViewById(R.id.tvProgressTitle)
    }

    private fun getProgressMessage(): AppCompatTextView? {
        return dialog?.findViewById(R.id.tvProgressMessage)
    }

    private fun getProgressBar(): ProgressBar? {
        return dialog?.findViewById(R.id.progressBar)
    }

    fun showMessage(title: String?, message: String) {
        getProgressTitle()?.text = if (title.isNullOrEmpty()) "Please wait" else title
        getProgressMessage()?.text = message
    }

    fun showSuccess(title: String?, message: String) {
        getProgressTitle()?.text = if (title.isNullOrEmpty()) "Success!" else title
        getProgressMessage()?.text = message
        getProgressBar()?.visibility = View.INVISIBLE
    }
}
