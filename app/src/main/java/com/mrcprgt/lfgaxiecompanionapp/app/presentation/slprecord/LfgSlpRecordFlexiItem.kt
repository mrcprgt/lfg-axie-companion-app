package com.mrcprgt.lfgaxiecompanionapp.app.presentation.slprecord

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mrcprgt.lfgaxiecompanionapp.R
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.LFGSlpRecordAndGains
import com.mrcprgt.lfgaxiecompanionapp.databinding.SlpCountListItemBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.BaseFlexibleItem
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.formatToHHMMA
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.formatToMMMMdd
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible

class LfgSlpRecordFlexiItem(val slpRecord: LFGSlpRecordAndGains) : BaseFlexibleItem() {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as LfgSlpRecordFlexiItem?
        if (slpRecord != other.slpRecord) return false
        return true
    }

    override fun hashCode(): Int {
        return slpRecord.hashCode()
    }

    override fun getLayoutRes() = R.layout.slp_count_list_item

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): BaseFlexibleViewHolder {
        return LFGSlpRecordVH(view, adapter)
    }

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: BaseFlexibleViewHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        if (holder is LFGSlpRecordVH) {
            holder.date.text = slpRecord.date.formatToMMMMdd()
            holder.time.text = "at ${slpRecord.date.formatToHHMMA()}"
            holder.slp.text = slpRecord.gains.toString()
        }
    }

    class LFGSlpRecordVH(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ) : BaseFlexibleViewHolder(view, adapter) {

        private val binding = SlpCountListItemBinding.bind(view)
        val date = binding.tvDate
        val time = binding.tvTime
        val slp = binding.tvSlpCount
    }
}
