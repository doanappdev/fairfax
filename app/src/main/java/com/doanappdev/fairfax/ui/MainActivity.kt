package com.doanappdev.fairfax.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.doanappdev.deloittetest.ui.adapter.NewsAdapter
import com.doanappdev.fairfax.FairfaxApp
import com.doanappdev.fairfax.R
import com.doanappdev.fairfax.base.ItemView
import com.doanappdev.fairfax.data.NewsRepository
import com.doanappdev.fairfax.util.WebViewUtil
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
        setNewsItemObserver()
        setProgressBarObserver()
        setItemClickObserver()
        viewModel?.getNewsArticles(repository)

    }

    override fun onResume() {
        super.onResume()
        viewModel?.registerClickEvents()
    }

    override fun onStop() {
        super.onStop()
        viewModel?.clear()
    }

    /**
     * observer for changes in NewsViewModel field 'newsItems' when
     * newsItems.value = sortedNewsItems
     * is call this Observer object is fired and setAdapter is called
     */
    private fun setNewsItemObserver() {
        viewModel?.newsItems?.observe(this, Observer<MutableList<ItemView>>{
            it?.let {  setAdapter(it) }
        })
    }

    private fun setProgressBarObserver() {
        viewModel?.isShowProgressBar?.observe(this, Observer<Boolean> {
            it?.let {
                when(it) {
                    true -> showProgressBar()
                    false -> hideProgressBar()
                }
            }
        })
    }

    private fun setItemClickObserver() {
        viewModel?.itemClick?.observe(this, Observer<String> {
            it?.let {
                WebViewUtil.openExternalUrl(this, it)
            }
        })
    }

    private fun setAdapter(items: List<ItemView>) {
        hideProgressBar()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = NewsAdapter(items)
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }
}
