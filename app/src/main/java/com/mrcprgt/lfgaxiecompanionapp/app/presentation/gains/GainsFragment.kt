package com.mrcprgt.lfgaxiecompanionapp.app.presentation.gains

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentInfoBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGFragment

class GainsFragment : LFGFragment(){
    private val binding: FragmentInfoBinding by lazy {
        FragmentInfoBinding.inflate(layoutInflater)
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

    }

    companion object{
        fun newInstance(): GainsFragment {
            val args = Bundle()
            val fragment = GainsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}