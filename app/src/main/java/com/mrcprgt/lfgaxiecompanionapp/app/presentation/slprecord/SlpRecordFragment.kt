package com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.stats.StatsFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.stats.StatsPresenter
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentSlpTrackerBinding
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentStatsBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import javax.inject.Inject

class SlpRecordFragment : LFGFragment(), SlpRecordContract.View {

    @Inject
    lateinit var presenter: SlpRecordPresenter

    private val binding: FragmentSlpTrackerBinding by lazy {
        FragmentSlpTrackerBinding.inflate(layoutInflater)
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

        presenter.onViewReady(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetach()
    }

    override fun showSlpRecords(slpRecords: List<SlpRecord>) {
        TODO("Not yet implemented")
    }

    override fun showDaily(daily: Int) {
        TODO("Not yet implemented")
    }

    override fun showWeekly(weekly: Int) {
        TODO("Not yet implemented")
    }

    override fun showMonthly(monthly: Int) {
        TODO("Not yet implemented")
    }

    override fun appendList(slpRecords: List<SlpRecord>) {
        TODO("Not yet implemented")
    }

    companion object{
        fun newInstance(): SlpRecordFragment {
            val args = Bundle()
            val fragment = SlpRecordFragment()
            fragment.arguments = args
            return fragment
        }
    }
}