package com.lfgcompany.lfgaxiecompanionapp.app.presentation.pvpbuddy

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.lfgcompany.lfgaxiecompanionapp.R
import com.lfgcompany.lfgaxiecompanionapp.databinding.EnergyCounterDialogBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base.BaseDialogFragment

class EnergyCounterDialog : BaseDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding = EnergyCounterDialogBinding.inflate(requireActivity().layoutInflater)
        val dialog = AlertDialog.Builder(requireActivity(), R.style.LFG_AlertDialog)

        dialog.setView(binding.root)
        dialog.setCancelable(false)

        var energy = 3

        binding.btnNegative.setOnClickWithDelay {
            if (energy > 0) {
                energy--
                binding.tvEnergy.text = "$energy"
            }
        }
        binding.btnPositive.setOnClickWithDelay {
            if (energy <= 10) {
                energy++
                binding.tvEnergy.text = "$energy"
            }
        }

        binding.btnAdd.setOnClickWithDelay {
            dismissAllowingStateLoss()
        }

        binding.btnSync.setOnClickWithDelay {
            energy = 3
            binding.tvEnergy.text = "$energy"
        }


        return dialog.create()
    }

}
