package com.mrcprgt.lfgaxiecompanionapp.app.presentation.gains

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mrcprgt.lfgaxiecompanionapp.R
import com.mrcprgt.lfgaxiecompanionapp.app.domain.models.SlpRecord
import com.mrcprgt.lfgaxiecompanionapp.databinding.InfoListItemBinding
import com.mrcprgt.lfgaxiecompanionapp.databinding.SlpCountListItemBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.*
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible

class InfoFlexiItem(val info: Info) : BaseFlexibleItem() {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as InfoFlexiItem?
        if (info != other.info) return false
        return true
    }

    override fun hashCode(): Int {
        return info.hashCode()
    }

    override fun getLayoutRes() = R.layout.info_list_item

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): BaseFlexibleViewHolder {
        return InfoItemViewHolder(view, adapter)
    }

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: BaseFlexibleViewHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        if (holder is InfoItemViewHolder) {
            when (info.type) {
                1 -> holder.tvLabel.setTextColor(
                    ContextCompat.getColor(
                        holder.contentView.context,
                        R.color.login_btn_label
                    )
                )
                2 -> holder.tvLabel.setTextColor(
                    ContextCompat.getColor(
                        holder.contentView.context,
                        R.color.claims_color
                    )
                )
                3 -> holder.tvLabel.setTextColor(
                    ContextCompat.getColor(
                        holder.contentView.context,
                        R.color.exp_label
                    )
                )
            }
            if (position % 2 == 0) {
                holder.tvLabel.text = info.label
                holder.tvLabel.setBackgroundResource(R.drawable.left_only_1)
                holder.tvValue.text = info.value
                holder.tvValue.setBackgroundResource(R.drawable.right_only_1)
            } else {
                holder.tvLabel.text = info.label
                holder.tvLabel.setBackgroundResource(R.drawable.left_only_2)
                holder.tvValue.text = info.value
                holder.tvValue.setBackgroundResource(R.drawable.right_only_2)
            }
        }
    }

    class InfoItemViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ) : BaseFlexibleViewHolder(view, adapter) {

        private val binding = InfoListItemBinding.bind(view)
        val tvLabel = binding.tvInfoLabel
        val tvValue = binding.tvInfoValue
    }
}
