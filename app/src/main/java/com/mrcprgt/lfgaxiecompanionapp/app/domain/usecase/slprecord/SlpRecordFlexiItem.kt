package com.mrcprgt.lfgaxiecompanionapp.app.domain.usecase.slprecord

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mrcprgt.lfgaxiecompanionapp.R
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.databinding.SlpCountListItemBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.BaseFlexibleItem
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.formatToMMDDYYYATHHMMAAAA
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible

class SlpRecordFlexiItem(val slpRecord: SlpRecord) : BaseFlexibleItem() {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as SlpRecordFlexiItem?
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
        return SlpRecordItemViewHolder(view, adapter)
    }

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: BaseFlexibleViewHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        if (holder is SlpRecordItemViewHolder) {
            holder.date.text = slpRecord.date.formatToMMDDYYYATHHMMAAAA()
            holder.slp.text = slpRecord.amount.toString()
        }
    }

    class SlpRecordItemViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ) : BaseFlexibleViewHolder(view, adapter) {

        private val binding = SlpCountListItemBinding.bind(view)
        val date = binding.tvDate
        val slp = binding.tvSlpCount
    }
}