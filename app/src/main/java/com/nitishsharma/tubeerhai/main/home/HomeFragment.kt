package com.nitishsharma.tubeerhai.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nitishsharma.tubeerhai.api.models.Beer
import com.nitishsharma.tubeerhai.databinding.FragmentHomeBinding
import com.nitishsharma.tubeerhai.main.bottomsheet.DetailedBottomSheet
import com.nitishsharma.tubeerhai.paging.BeerLoaderAdapter
import com.nitishsharma.tubeerhai.paging.BeerPagingAdapter
import com.nitishsharma.tubeerhai.utility.Utility.shareOnWhatsapp

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: BeerPagingAdapter
    private lateinit var dialog: BottomSheetDialog
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeBinding.inflate(inflater, container, false).also {
        binding = it
        dialog = BottomSheetDialog(requireContext())
        initViews()
    }.root

    private fun initViews() {
        adapter = BeerPagingAdapter(object : BeerPagingAdapter.ClickListeners {
            override fun onItemLongClickListener(item: Beer) {
                openBottomSheetFragment(item)
            }

            override fun onWhatsappClickListener(item: Beer) {
                val textToShareOnWhatsapp =
                    "Hey, checkout this new beer called ${item.name}. It has ${item.abv}% of alcohol in it and will make you dance like no one else.\nClick on the link to see the picture ${item.image_url}"
                shareOnWhatsapp(textToShareOnWhatsapp)
            }

        })
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = BeerLoaderAdapter(),
            footer = BeerLoaderAdapter()
        )
    }

    private fun openBottomSheetFragment(item: Beer) {
        val bottomSheetDialogFragment = DetailedBottomSheet.newInstance(item)
        bottomSheetDialogFragment.show(childFragmentManager, "DETAILED_BOTTOM_SHEET")
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