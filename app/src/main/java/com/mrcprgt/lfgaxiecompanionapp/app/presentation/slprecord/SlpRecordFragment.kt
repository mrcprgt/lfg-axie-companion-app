package com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord.SlpRecordFlexiItem
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.stats.StatsFragment
import com.mrcprgt.lfgaxiecompanionapp.app.presentation.stats.StatsPresenter
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentSlpTrackerBinding
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentStatsBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import eu.davidea.flexibleadapter.FlexibleAdapter
import javax.inject.Inject

class SlpRecordFragment : LFGFragment(), SlpRecordContract.View {

    @Inject
    lateinit var presenter: SlpRecordPresenter

    private val binding: FragmentSlpTrackerBinding by lazy {
        FragmentSlpTrackerBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        FlexibleAdapter(emptyList())
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
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetach()
    }

    override fun showSlpRecords(slpRecords: List<SlpRecord>) {
        adapter.updateDataSet(slpRecords.map {
            SlpRecordFlexiItem(
                it
            )
        })
    }

    override fun showDaily(daily: Int) {
        binding.tvDailyAverage.text = daily.toString()
    }

    override fun showWeekly(weekly: Int) {
        binding.tvWeeklyAverage.text = weekly.toString()
    }

    override fun showMonthly(monthly: Int) {
        binding.tvMonthlyAverage.text = monthly.toString()
    }

    override fun showCurrentCycle(manager: Int, scholar: Int) {
        binding.tvScholarShare.text = scholar.toString()
        binding.tvManagerShare.text = manager.toString()
    }

    override fun showLifetimeSlp(manager: Int, scholar: Int) {
        binding.tvTotalScholarEarned.text = scholar.toString()
        binding.tvTotalManagerEarned.text = manager.toString()
    }

    override fun appendList(slpRecords: List<SlpRecord>) {
        adapter.updateDataSet(slpRecords.map {
            SlpRecordFlexiItem(
                it
            )
        })
    }

    private fun setupRecyclerView() {
        binding.rvSlp.adapter = adapter
        binding.rvSlp.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        fun newInstance(): SlpRecordFragment {
            val args = Bundle()
            val fragment = SlpRecordFragment()
            fragment.arguments = args
            return fragment
        }
    }
}