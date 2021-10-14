package com.lfgcompany.lfgaxiecompanionapp.app.presentation.slprecord

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.lfgcompany.lfgaxiecompanionapp.databinding.FragmentSlpTrackerBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.showAllowingStateLoss
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
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
            presenter.onSyncClicked()
        }

        binding.btnAdd.setOnClickWithDelay {
            presenter.onAddButtonClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetach()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        presenter.onViewReady(this)
    }

    override fun showAddDialog() {
        dialog.setupListener(this)
        dialog.showAllowingStateLoss(parentFragmentManager, "add_record")
    }

    override fun showSlpRecords(slpRecords: List<SlpRecord>) {
        adapter.updateDataSet(slpRecords.map {
            SlpRecordFlexiItem(
                it
            )
        })
    }

    override fun showToday(today: Int) {
        binding.tvTodaySlp.text = "$today"
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

    @SuppressLint("SetTextI18n")
    override fun showCurrentCycle(manager: Int, scholar: Int) {
        binding.tvScholarShare.text = "Scholar: $scholar"
        binding.tvManagerShare.text = "Manager: $manager"
    }

    @SuppressLint("SetTextI18n")
    override fun showLifetimeSlp(manager: Int, scholar: Int) {
        binding.tvLifetimeScholarShare.text = "Scholar: $scholar                        "
        binding.tvLifetimeManagerShare.text = "Manager: $manager"
    }

    override fun appendList(slpRecords: List<LFGSlpRecordAndGains>) {
        Log.e("slp", slpRecords.toString())
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