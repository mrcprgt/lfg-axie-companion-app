package com.lfgcompany.lfgaxiecompanionapp.app.presentation.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lfgcompany.lfgaxiecompanionapp.databinding.BottomSheetSettingsBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay

class MenuBottomSheet : BottomSheetDialogFragment() {

    private var listener: MenuBottomSheetListener? = null

    val binding: BottomSheetSettingsBinding by lazy {
        BottomSheetSettingsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogout.setOnClickWithDelay {
            listener?.onLogoutClicked()
        }

        binding.btnChangeSetting.setOnClickWithDelay {
            listener?.onChangeTrackingClick()
        }
    }

    private fun setListener(listener: MenuBottomSheetListener) {
        this.listener = listener
    }

    companion object {
        fun newInstance(listener: MenuBottomSheetListener): MenuBottomSheet {
            val fragment = MenuBottomSheet()
            fragment.setListener(listener)
            return fragment
        }
    }
}

interface MenuBottomSheetListener {
    fun onLogoutClicked()
    fun onChangeTrackingClick()
}