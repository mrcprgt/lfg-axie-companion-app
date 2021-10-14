package com.lfgcompany.lfgaxiecompanionapp.app.presentation.pvpbuddy

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.lfgcompany.lfgaxiecompanionapp.R
import com.lfgcompany.lfgaxiecompanionapp.app.domain.models.PvpRecord
import com.lfgcompany.lfgaxiecompanionapp.databinding.PvpRecordListItemBinding
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.BaseFlexibleItem
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.capitalizeEachWord
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.formatToHHMMA
import com.lfgcompany.lfgaxiecompanionapp.tools.helpers.formatToMMMMdd
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible

class PvpRecordFlexiItem(val pvpRecord: PvpRecord) : BaseFlexibleItem() {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as PvpRecordFlexiItem?
        if (pvpRecord != other.pvpRecord) return false
        return true
    }

    override fun hashCode(): Int {
        return pvpRecord.hashCode()
    }

    override fun getLayoutRes() = R.layout.pvp_record_list_item

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): BaseFlexibleViewHolder {
        return PvpRecordFlexiItem.PvpRecordFlexiItemViewHolder(view, adapter)
    }

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: BaseFlexibleViewHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        if (holder is PvpRecordFlexiItem.PvpRecordFlexiItemViewHolder) {
            when (pvpRecord.pvpResult) {
                PvpRecord.PvpResult.WIN -> holder.result.setTextColor(
                    ContextCompat.getColor(
                        holder.contentView.context,
                        R.color.green
                    )
                )
                PvpRecord.PvpResult.DRAW ->
                    ContextCompat.getColor(
                        holder.contentView.context,
                        R.color.arena_color
                    )
                PvpRecord.PvpResult.LOSE ->
                    ContextCompat.getColor(
                        holder.contentView.context,
                        R.color.claims_label
                    )
            }
            holder.result.text = pvpRecord.pvpResult.name.capitalizeEachWord()
            holder.date.text = pvpRecord.date.formatToMMMMdd()
            holder.time.text = "at ${pvpRecord.date.formatToHHMMA()}"
            holder.slp.text = pvpRecord.slpEarned.toString()
        }
    }

    class PvpRecordFlexiItemViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ) : BaseFlexibleViewHolder(view, adapter) {

        private val binding = PvpRecordListItemBinding.bind(view)
        val result = binding.tvPvpRecordStatus
        val date = binding.tvDate
        val time = binding.tvTime
        val slp = binding.tvSlpCount
    }
}
