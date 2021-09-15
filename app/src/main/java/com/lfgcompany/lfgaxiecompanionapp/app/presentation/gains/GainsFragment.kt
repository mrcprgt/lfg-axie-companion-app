package com.lfgcompany.lfgaxiecompanionapp.app.presentation.gains

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfgcompany.lfgaxiecompanionapp.databinding.FragmentInfoBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import eu.davidea.flexibleadapter.FlexibleAdapter

class GainsFragment : LFGFragment() {
    private val binding: FragmentInfoBinding by lazy {
        FragmentInfoBinding.inflate(layoutInflater)
    }

    private val adventureSlpAdapter by lazy {
        FlexibleAdapter(emptyList())
    }

    private val arenaSlpAdapter by lazy {
        FlexibleAdapter(emptyList())
    }

    private val adventureExpAdapter by lazy {
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

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        binding.rvAdventureModeSlp.adapter = adventureSlpAdapter
        binding.rvAdventureModeSlp.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArena.adapter = arenaSlpAdapter
        binding.rvArena.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExp.adapter = adventureExpAdapter
        binding.rvExp.layoutManager = LinearLayoutManager(requireContext())

        adventureSlpAdapter.updateDataSet(
            adventureSlpGain.map {
                InfoFlexiItem(it)
            }
        )


        adventureExpAdapter.updateDataSet(
            adventureExpGain.map {
                InfoFlexiItem(it)
            }
        )

        arenaSlpAdapter.updateDataSet(
            arenaSlpGain.map {
                InfoFlexiItem(it)
            }
        )
    }

    private val adventureSlpGain = listOf(
        Info("1-4", "1", 1),
        Info("5-9", "2", 1),
        Info("10-14", "4", 1),
        Info("15-16", "6", 1),
        Info("17-20", "5-10", 1),
        Info("21-36", "10-20", 1),
    )


    private val arenaSlpGain = listOf(
        Info("0-799", "0", 2),
        Info("800-999", "1", 2),
        Info("1000-1199", "3", 2),
        Info("1100-1299", "6", 2),
        Info("1300-1499", "9", 2),
        Info("1500-1799", "12", 2),
        Info("1800-1999", "15", 2),
        Info("2000-2199", "18", 2),
        Info("2200+", "21", 2),
    )


    private val adventureExpGain = listOf(
        Info("1", "51", 3),
        Info("2", "59", 3),
        Info("3", "111", 3),
        Info("4", "118*", 3),
        Info("5", "141", 3),
        Info("6", "199", 3),
        Info("7", "256*", 3),
        Info("8", "237", 3),
        Info("9", "282", 3),
        Info("10", "300", 3),
        Info("11", "344*", 3),
        Info("12", "429", 3),
        Info("13", "378", 3),
        Info("14", "347", 3),
        Info("15", "358", 3),
        Info("16", "402", 3),
        Info("17", "367", 3),
        Info("18", "445*", 3),
        Info("19", "434", 3),
        Info("20", "401", 3),
        Info("21", "644", 3),
        Info("22", "449", 3),
        Info("23", "618", 3),
        Info("24", "582", 3),
        Info("25", "618", 3),
        Info("26", "797", 3),
        Info("27", "735*", 3),
        Info("28", "759", 3),
        Info("29", "800", 3),
        Info("30", "1209", 3),
        Info("31", "810", 3),
        Info("32", "724", 3),
        Info("33", "891", 3),
        Info("34", "?", 3),
        Info("35", "805", 3),
        Info("36", "820", 3),
    )


    companion object {
        fun newInstance(): GainsFragment {
            val args = Bundle()
            val fragment = GainsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}