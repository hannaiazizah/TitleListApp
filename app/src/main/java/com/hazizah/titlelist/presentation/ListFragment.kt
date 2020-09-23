package com.hazizah.titlelist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hazizah.titlelist.databinding.FragmentListBinding
import com.hazizah.titlelist.domain.Resource.Status.Companion.ERROR
import com.hazizah.titlelist.domain.Resource.Status.Companion.LOADING
import com.hazizah.titlelist.domain.Resource.Status.Companion.SUCCESS
import com.hazizah.titlelist.domain.gone
import com.hazizah.titlelist.domain.show
import org.koin.android.viewmodel.ext.android.viewModel


class ListFragment : Fragment() {
    private val adapter by lazy { TitleAdapter() }
    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModel<ArticleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // set adapter
        binding.titleList.apply {
            val divider = DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
            adapter = this@ListFragment.adapter
            addItemDecoration(divider)
        }

        binding.refreshLayout.setOnRefreshListener { viewModel.getData() }

        binding.searchLayout.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            var hasSubmit = false
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.search(query)
                    hasSubmit = true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() == true && hasSubmit) {
                    viewModel.getData()
                    hasSubmit = false
                }
                return false
            }

        })

        viewModel.getData()
        viewModel.articleList.observe(viewLifecycleOwner, Observer { resources ->
            when(resources.status) {
                LOADING -> {
                    binding.apply {
                        refreshLayout.isRefreshing = true
                        titleList.gone()
                        txtNoData.gone()
                    }
                }
                ERROR -> {
                    binding.apply {
                        refreshLayout.isRefreshing = false
                        titleList.gone()
                        txtNoData.show()
                    }
                }
                SUCCESS -> {
                    binding.apply {
                        refreshLayout.isRefreshing = false
                        titleList.show()
                        txtNoData.gone()
                    }
                    resources.data?.let {
                        adapter.setTitles(it.map { article -> article.title })
                    }
                }
            }

        })
    }
}