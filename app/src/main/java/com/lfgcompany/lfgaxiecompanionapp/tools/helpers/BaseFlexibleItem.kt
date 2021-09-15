package com.lfgcompany.lfgaxiecompanionapp.tools.helpers

import android.view.View
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.viewholders.FlexibleViewHolder

abstract class BaseFlexibleItem : AbstractFlexibleItem<BaseFlexibleItem.BaseFlexibleViewHolder>() {
    abstract class BaseFlexibleViewHolder(
        view: View,
        flexibleAdapter: FlexibleAdapter<*>,
        isSticky: Boolean? = false
    ) : FlexibleViewHolder(view, flexibleAdapter, isSticky ?: false)
}
