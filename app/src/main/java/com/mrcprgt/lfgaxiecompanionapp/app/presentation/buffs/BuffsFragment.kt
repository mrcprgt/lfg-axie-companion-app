package com.mrcprgt.lfgaxiecompanionapp.app.presentation.buffs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrcprgt.lfgaxiecompanionapp.databinding.FragmentBuffsBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.mvp.base.LFGFragment
import eu.davidea.flexibleadapter.FlexibleAdapter

class BuffsFragment : LFGFragment() {
    private val binding: FragmentBuffsBinding by lazy {
        FragmentBuffsBinding.inflate(layoutInflater)
    }

    private val buffsAdapter by lazy {
        FlexibleAdapter(emptyList())
    }

    private val debuffsAdapter by lazy {
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

    private val buffs = listOf(
        Buff(1, "Attack Up", "Increase the next Attack by 20%.\nStackable"),
        Buff(2, "Morale Up", "Increase Morale by 20%.\nStackable"),
        Buff(3, "Speed Up", "Increase Speed by 20% for the next round."),
    )

    private val debuffs = listOf(
        Buff(4, "Aroma", "Target priority changes to affected Axie for the next round."),
        Buff(5, "Attack Down", "Decreases next Attack by 20%.\nStackable"),
        Buff(6, "Chill", "Affected Axie can't enter last stand."),
        Buff(7, "Fear", "Affected Axie can't attack."),
        Buff(8, "Fragile", "Shield takes double the damage for the next incoming attack."),
        Buff(9, "Jinx", "Affected Axie can't land critical hits for the next round."),
        Buff(10, "Lethal", "Next hit against affected Axie is critical."),
        Buff(11, "Morale Down", "Decreases Morale by 20% for the next round.\nStackable"),
        Buff(12, "Poison", "Until removal, affected Axie loses 2 HP for every action.\nStackable"),
        Buff(13, "Sleep", "Next incoming attack ignores shields."),
        Buff(14, "Speed Down", "Decreases speed by 20% for the next round.\nStackable"),
        Buff(15, "Stench", "Affected Axie loses target priority for the next round."),
        Buff(16, "Stun", "Next attack misses / Next incoming attack ignores shields."),
    )

    private fun setupRecyclerView() {
        binding.rvBuffs.adapter = buffsAdapter
        binding.rvBuffs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDebuffs.adapter = debuffsAdapter
        binding.rvDebuffs.layoutManager = LinearLayoutManager(requireContext())

        buffsAdapter.updateDataSet(
            buffs.map {
                BuffFlexiItem(it)
            }
        )

        debuffsAdapter.updateDataSet(
            debuffs.map {
                BuffFlexiItem(it)
            }
        )
    }

    companion object {
        fun newInstance(): BuffsFragment {
            val args = Bundle()
            val fragment = BuffsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
