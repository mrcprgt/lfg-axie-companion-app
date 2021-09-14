package com.mrcprgt.lfgaxiecompanionapp.app.presentation.buffs

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mrcprgt.lfgaxiecompanionapp.R
import com.mrcprgt.lfgaxiecompanionapp.databinding.BuffListItemBinding
import com.mrcprgt.lfgaxiecompanionapp.tools.helpers.BaseFlexibleItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible

class BuffFlexiItem(val buff: Buff) : BaseFlexibleItem() {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as BuffFlexiItem?
        if (buff != other.buff) return false
        return true
    }

    override fun hashCode(): Int {
        return buff.hashCode()
    }

    override fun getLayoutRes() = R.layout.buff_list_item

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): BaseFlexibleViewHolder {
        return BuffItemViewHolder(view, adapter)
    }

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: BaseFlexibleViewHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        if (holder is BuffItemViewHolder) {

            holder.tvName.text = buff.name
            holder.tvInfo.text = buff.details

            if (buff.id in 1..3) {
                holder.tvName.setTextColor(
                    ContextCompat.getColor(
                        holder.container.context,
                        R.color.login_btn_label
                    )
                )
            } else {
                holder.tvName.setTextColor(
                    ContextCompat.getColor(
                        holder.container.context,
                        R.color.claims_color
                    )
                )
            }
            if (position % 2 == 0) {
                holder.container.setBackgroundResource(R.drawable.left_only_1)
                holder.tvName.setBackgroundResource(R.color.card_bg_1)
                holder.tvInfo.setBackgroundResource(R.drawable.right_only_1)
            } else {
                holder.container.setBackgroundResource(R.drawable.left_only_2)
                holder.tvName.setBackgroundResource(R.color.card_bg_2)
                holder.tvInfo.setBackgroundResource(R.drawable.right_only_2)
            }
            when (buff.id) {
                1 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.buff_attack_up
                        )
                    )
                }
                2 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.buff_morale_up
                        )
                    )
                }
                3 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.buff_speed_up
                        )
                    )
                }
                4 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_aroma
                        )
                    )
                }
                5 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_attack_down
                        )
                    )
                }
                6 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_chill
                        )
                    )
                }
                7 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_fear
                        )
                    )
                }
                8 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_fragile
                        )
                    )
                }
                9 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_jinx
                        )
                    )
                }
                10 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_lethal
                        )
                    )
                }
                11 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_morale_down
                        )
                    )
                }
                12 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_poison
                        )
                    )
                }
                13 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_sleep
                        )
                    )
                }
                14 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_speed_down
                        )
                    )
                }
                15 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_stench
                        )
                    )
                }
                16 -> {
                    holder.ivIcon.setImageDrawable(
                        ContextCompat.getDrawable(
                            holder.container.context,
                            R.drawable.debuff_stun
                        )
                    )
                }
            }
        }
    }

    class BuffItemViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ) : BaseFlexibleViewHolder(view, adapter) {

        private val binding = BuffListItemBinding.bind(view)
        val container = binding.clParent
        val ivIcon = binding.ivBuffIcon
        val tvName = binding.tvBuffName
        val tvInfo = binding.tvBuffInfo
    }
}
