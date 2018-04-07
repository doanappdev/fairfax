package com.doanappdev.fairfax.data.models

import com.doanappdev.fairfax.base.ItemView


val ITEM_ASSET = 0

data class NewsItem(val asset: Asset) : ItemView {
    override fun getItemType() = ITEM_ASSET
}
