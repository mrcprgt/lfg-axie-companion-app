package com.lfgcompany.lfgaxiecompanionapp.app.presentation.slprecord

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.lfgcompany.lfgaxiecompanionapp.R
import com.lfgcompany.lfgaxiecompanionapp.databinding.CheckinDialogBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base.BaseDialogFragment

class AddRecordDialog : BaseDialogFragment(){
    private lateinit var onAddRecordLister: OnAddRecordListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding = CheckinDialogBinding.inflate(requireActivity().layoutInflater)
        val dialog = AlertDialog.Builder(requireActivity(), R.style.LFG_AlertDialog)

        dialog.setView(binding.root)
        dialog.setCancelable(false)

        binding.btnNegative.setOnClickWithDelay {
            this.dismissAllowingStateLoss()
        }
        binding.btnPositive.setOnClickWithDelay {
            onAddRecordLister.onAddClicked(
                binding.inputDailySlpEarned.editText!!.text.toString().toInt()
            )
        }


        return dialog.create()
    }

    fun setupListener(listener: OnAddRecordListener){
        this.onAddRecordLister = listener
    }

    interface OnAddRecordListener{
        fun onAddClicked(slp: Int)
    }
}