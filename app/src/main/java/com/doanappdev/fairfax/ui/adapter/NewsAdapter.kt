package com.doanappdev.deloittetest.ui.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.doanappdev.fairfax.base.ItemView
import com.doanappdev.fairfax.base.ViewTypeDelegateAdapter
import com.doanappdev.fairfax.ui.adapter.NewsDelegateAdapter
import com.doanappdev.fairfax.data.models.ITEM_ASSET

class NewsAdapter(private val viewItems: List<ItemView>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        // we can add other delegate adapters here to handle different view types
        // eg. if we wanted to display image view with round borders
        delegateAdapters.put(ITEM_ASSET, NewsDelegateAdapter())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, viewItems[position])
    }

    override fun getItemViewType(position: Int) = viewItems[position].getItemType()
    override fun getItemCount(): Int = viewItems.count()
}
