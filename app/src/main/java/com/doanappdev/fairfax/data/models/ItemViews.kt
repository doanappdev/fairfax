package com.doanappdev.fairfax.data.models

import com.doanappdev.fairfax.base.ItemView
import io.reactivex.subjects.PublishSubject


val ITEM_ASSET = 0

data class NewsItem(val asset: Asset) : ItemView {

    val clickSubject : PublishSubject<String> = PublishSubject.create()

    override fun getItemType() = ITEM_ASSET
}
