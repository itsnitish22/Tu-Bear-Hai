package com.nitishsharma.tubeerhai.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nitishsharma.tubeerhai.R

class BeerLoaderAdapter : LoadStateAdapter<BeerLoaderAdapter.LoaderViewHolder>() {
    //getting views data
    class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        fun bind(loadState: LoadState) { //setting visibility according to load state
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    //binding loader with load state
    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    //onCreateVH
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loader, parent, false)
        return LoaderViewHolder(view)
    }
}