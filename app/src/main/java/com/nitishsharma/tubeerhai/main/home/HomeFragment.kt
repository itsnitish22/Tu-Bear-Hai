package com.nitishsharma.tubeerhai.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nitishsharma.tubeerhai.api.models.Beer
import com.nitishsharma.tubeerhai.databinding.FragmentHomeBinding
import com.nitishsharma.tubeerhai.paging.BeerPagingAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: BeerPagingAdapter
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeBinding.inflate(inflater, container, false).also {
        binding = it
        initViews()
    }.root

    private fun initViews() {
        adapter = BeerPagingAdapter(object : BeerPagingAdapter.OnLongClickListener {
            override fun onItemLongClickListener(item: Beer) {
                TODO("Not yet implemented")
            }

        })
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.listOfBeers.observe(requireActivity(), Observer {
            adapter.submitData(lifecycle, it)
        })
    }
}