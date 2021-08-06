package com.mrcprgt.lfgaxiecompanionapp.app.presentation.buffs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentBuffsBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGFragment

class BuffsFragment : LFGFragment(){
    private val binding: FragmentBuffsBinding by lazy {
        FragmentBuffsBinding.inflate(layoutInflater)
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
        fun newInstance(): BuffsFragment {
            val args = Bundle()
            val fragment = BuffsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
