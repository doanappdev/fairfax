package com.doanappdev.fairfax.ui.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.doanappdev.fairfax.R
import com.doanappdev.fairfax.base.ItemView
import com.doanappdev.fairfax.base.ViewTypeDelegateAdapter
import com.doanappdev.fairfax.base.inflate
import com.doanappdev.fairfax.data.models.NewsItem
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.item_news.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class NewsDelegateAdapter : ViewTypeDelegateAdapter, AnkoLogger {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ItemView) {
        holder as NewsViewHolder
        holder.bind(item as NewsItem)
    }

    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_news)) {
        fun bind(item: NewsItem) = with(itemView) {
            val asset = item.asset

            headLineTxt.text = asset.headline
            abstractTxt.text = asset.theAbstract
            byLineTxt.text = asset.byLine

            asset.findSmallestImage()

            Glide.with(context)
                    .load(asset.smallestImage?.url)
                    .placeholder(ContextCompat.getDrawable(context, R.mipmap.ic_launcher_round)) // temp placeholder in real app would use a different one
                    .into(imageView)

            this.clicks().subscribe { item.clickSubject.onNext(asset.url) }
        }
    }
}
