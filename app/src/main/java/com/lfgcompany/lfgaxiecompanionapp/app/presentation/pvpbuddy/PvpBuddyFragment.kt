package com.lfgcompany.lfgaxiecompanionapp.app.presentation.pvpbuddy

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord
import com.lfgcompany.lfgaxiecompanionapp.app.presentation.floatingwindowservice.AlternateWindowService
import com.lfgcompany.lfgaxiecompanionapp.databinding.FragmentPvpBuddyBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.setOnClickWithDelay
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.showAllowingStateLoss
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.MessageDialog
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import eu.davidea.flexibleadapter.FlexibleAdapter
import javax.inject.Inject

class PvpBuddyFragment : LFGFragment(), PvpBuddyContract.View,
    AddPvpRecordDialog.OnAddPvpRecordClickListener {

    @Inject
    lateinit var presenter: PvpBuddyPresenter

    private val adapter by lazy {
        FlexibleAdapter(emptyList())
    }

    private val binding: FragmentPvpBuddyBinding by lazy {
        FragmentPvpBuddyBinding.inflate(layoutInflater)
    }

    private val dialog = EnergyCounterDialog()
    private val winDialog = AddPvpRecordDialog(PvpRecord.PvpResult.WIN)
    private val drawDialog = AddPvpRecordDialog(PvpRecord.PvpResult.DRAW)
    private val loseDialog = AddPvpRecordDialog(PvpRecord.PvpResult.LOSE)

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

        binding.parent.requestDisallowInterceptTouchEvent(true)
        setupListeners()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvPvpRecords.adapter = adapter
        binding.rvPvpRecords.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupListeners() {
        binding.btnAddWin.setOnClickWithDelay {
            winDialog.setupListener(this)
            winDialog.showAllowingStateLoss(parentFragmentManager, "add_win")
        }

        binding.btnAddDraw.setOnClickWithDelay {
            drawDialog.setupListener(this)
            drawDialog.showAllowingStateLoss(parentFragmentManager, "add_draw")
        }

        binding.btnAddLose.setOnClickWithDelay {
            loseDialog.setupListener(this)
            loseDialog.showAllowingStateLoss(parentFragmentManager, "add_lose")
        }

        binding.btnClear.setOnClickWithDelay {
            MessageDialog()
                .title("Are you sure you want to clear?")
                .message("This will clear all your local pvp data on the app.")
                .positiveButton("Yes", onClick = {
                    presenter.onClearClicked()
                })
                .negativeButton("No", onClick = {})
        }

        binding.btnEnergy.setOnClickWithDelay {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(
                    requireContext()
                )
            ) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:com.lfgcompany.lfgaxiecompanionapp")
                )
                startActivityForResult(intent, 201)
            } else {
                requireActivity().startService(
                    Intent(
                        context,
                        AlternateWindowService::class.java
                    )
                )
                requireActivity().finish()
            }

//            showEnergyCounter()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            activity?.startService(Intent(requireContext(), AlternateWindowService::class.java))
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetach()
    }

    override fun updateWins(wins: Int) {
        binding.tvWins.text = wins.toString()
    }

    override fun updateDraws(draws: Int) {
        binding.tvDraw.text = draws.toString()
    }

    override fun updateLoses(loses: Int) {
        binding.tvLose.text = loses.toString()
    }

    override fun updateTotalMatchesPlayed(matchesPlayed: Int) {
        binding.tvTotalMatchesPlayed.text = matchesPlayed.toString()
    }

    @SuppressLint("SetTextI18n")
    override fun updateWinRate(winRate: Double) {
        if (winRate == 0.0) {
            binding.tvWinRate.text = "--"
        } else {
            binding.tvWinRate.text = "${winRate * 100} %"
        }
    }

    override fun updateTotalSlpEarned(totalSlp: Int) {
        binding.tvTotalSlpEarned.text = totalSlp.toString()
    }

    override fun updateList(list: List<PvpRecord>) {
        adapter.updateDataSet(list.map {
            PvpRecordFlexiItem(
                it
            )
        })
    }

    override fun clearList() {
        adapter.clear()
    }

    override fun dismissDialogs() {
        when {
            winDialog.isVisible -> {
                winDialog.dismissAllowingStateLoss()
            }
            drawDialog.isVisible -> {
                drawDialog.dismissAllowingStateLoss()
            }
            loseDialog.isVisible -> {
                loseDialog.dismissAllowingStateLoss()
            }

        }
    }

    private fun showEnergyCounter() {
        dialog.isCancelable = false
        dialog.showAllowingStateLoss(
            parentFragmentManager,
            "energy"
        )
    }

    companion object {
        fun newInstance(): PvpBuddyFragment {
            val args = Bundle()
            val fragment = PvpBuddyFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAddRecordClicked(pvpResult: PvpRecord.PvpResult, slp: Int) {
        presenter.onAddPressed(pvpResult, slp)
    }
}