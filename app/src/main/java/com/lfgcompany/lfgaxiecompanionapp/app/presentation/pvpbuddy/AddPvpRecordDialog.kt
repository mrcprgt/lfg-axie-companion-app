package com.lfgcompany.lfgaxiecompanionapp.app.presentation.pvpbuddy

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import com.lfgcompany.lfgaxiecompanionapp.R
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord
import com.lfgcompany.lfgaxiecompanionapp.databinding.SlpEarnedDialogBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base.BaseDialogFragment
import javax.inject.Inject

class AddPvpRecordDialog @Inject constructor(
    private val result: PvpRecord.PvpResult
) : BaseDialogFragment() {

    private lateinit var listener: OnAddPvpRecordClickListener

    @SuppressLint("SetTextI18n")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = SlpEarnedDialogBinding.inflate(requireActivity().layoutInflater)
        val dialog = AlertDialog.Builder(requireActivity(), R.style.LFG_AlertDialog)

        dialog.setView(binding.root)
        dialog.setCancelable(false)


        when (result) {
            PvpRecord.PvpResult.WIN -> {
                binding.tvMessage.text = "Are you sure you want to add a win?"
            }
            PvpRecord.PvpResult.DRAW -> {
                binding.tvMessage.text = "Are you sure you want to add a draw?"
            }
            PvpRecord.PvpResult.LOSE -> {
                binding.tvMessage.text = "Are you sure you want to add a lose?"
                binding.inputSlpEarned.visibility = View.INVISIBLE
            }
        }

        binding.btnNegative.setOnClickWithDelay {
            this.dismissAllowingStateLoss()
        }

        binding.btnPositive.setOnClickWithDelay {
            if (binding.inputSlpEarned.editText?.text!!.isBlank() && result != PvpRecord.PvpResult.LOSE) {
                binding.inputSlpEarned.isErrorEnabled = true
                binding.inputSlpEarned.error = "0 is not allowed!"
            } else {
                binding.inputSlpEarned.isErrorEnabled = false
                binding.inputSlpEarned.error = ""
                listener.onAddRecordClicked(
                    this.result,
                    binding.inputSlpEarned.editText?.text.toString().toIntOrNull() ?: 0
                )
                this.dismissAllowingStateLoss()
            }

        }

        return dialog.create()
    }

    fun setupListener(listener: OnAddPvpRecordClickListener) {
        this.listener = listener
    }

    interface OnAddPvpRecordClickListener {
        fun onAddRecordClicked(pvpResult: PvpRecord.PvpResult, slp: Int)
    }
}
