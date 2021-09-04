package com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentSlpTrackerBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.showAllowingStateLoss
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import eu.davidea.flexibleadapter.FlexibleAdapter
import javax.inject.Inject

class SlpRecordFragment : LFGFragment(), SlpRecordContract.View,
    AddRecordDialog.OnAddRecordListener {

    @Inject
    lateinit var presenter: SlpRecordPresenter

    private val dialog = AddRecordDialog()

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

        binding.btnSync.setOnClickWithDelay {
            showToast("This feature is coming soon!")
        }

        binding.btnAddRecord.setOnClickWithDelay {
            dialog.setupListener(this)
            dialog.showAllowingStateLoss(parentFragmentManager, "add_record")
        }
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

    override fun appendList(slpRecords: List<LFGSlpRecordAndGains>) {
        adapter.updateDataSet(slpRecords.map {
            LfgSlpRecordFlexiItem(
                it
            )
        })
    }

    override fun hideAddDialog() {
        dialog.dismissAllowingStateLoss()
    }

    override fun clearSlp() {
        adapter.clear()
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

    override fun onAddClicked(slp: Int) {
        presenter.onAddRecordClicked(slp)
    }
}