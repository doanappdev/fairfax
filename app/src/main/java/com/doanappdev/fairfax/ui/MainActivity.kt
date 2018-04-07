package com.doanappdev.fairfax.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.doanappdev.deloittetest.ui.adapter.NewsAdapter
import com.doanappdev.fairfax.FairfaxApp
import com.doanappdev.fairfax.R
import com.doanappdev.fairfax.base.ItemView
import com.doanappdev.fairfax.data.NewsRepository
import com.doanappdev.fairfax.viewmodels.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AnkoLogger {

    @Inject lateinit var repository: NewsRepository
    var viewModel: NewsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FairfaxApp.appComponent.inject(this)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel?.getNewsArticles(repository)
        viewModel?.newsItems?.observe(this, Observer<MutableList<ItemView>>{
            it?.let {  setAdapter(it) }
        })

    }

    fun setAdapter(items: List<ItemView>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            info { "size : ${items.size}" }
            adapter = NewsAdapter(items)
        }
    }
}
