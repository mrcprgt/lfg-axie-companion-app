package com.lfgcompany.lfgaxiecompanionapp.tools.mvp

import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.lfgcompany.lfgaxiecompanionapp.R
import com.lfgcompany.lfgaxiecompanionapp.databinding.LoadingDialogBinding

class SimpleLoadingDialog : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NO_FRAME,
            R.style.LFG_AlertDialog_Wrap
        )
    }
    override fun getTheme(): Int {
        return R.style.LFG_AlertDialog_Wrap
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = LoadingDialogBinding.inflate(inflater)
        isCancelable = false

        val attrs = intArrayOf(R.attr.colorPrimary, R.color.white)
        val typedValue = TypedValue()
        val typedArray = requireContext().obtainStyledAttributes(typedValue.data, attrs)
        val progressColor = typedArray.getColor(0, 0)
        typedArray.recycle()

        if (SDK_INT >= Build.VERSION_CODES.Q) {
            binding.progressBar.indeterminateDrawable.colorFilter =
                BlendModeColorFilter(progressColor, android.graphics.BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            binding.progressBar.indeterminateDrawable.setColorFilter(progressColor, PorterDuff.Mode.SRC_IN)
        }
        return binding.root
    }
}
