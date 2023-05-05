package com.nitishsharma.tubeerhai.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nitishsharma.tubeerhai.R
import com.nitishsharma.tubeerhai.api.models.Beer

class BeerPagingAdapter(
    private val itemLongClickListener: OnLongClickListener
) : PagingDataAdapter<Beer, BeerPagingAdapter.ViewHolder>(COMPARATOR) {

    interface OnLongClickListener {
        fun onItemLongClickListener(item: Beer)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beerName = itemView.findViewById<TextView>(R.id.beerName)
        val beerTagline = itemView.findViewById<TextView>(R.id.tagLine)
        val beerImage = itemView.findViewById<ImageView>(R.id.beerImage)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            Glide.with(holder.beerImage.context).load(item.image_url).diskCacheStrategy(
                DiskCacheStrategy.DATA
            ).into(holder.beerImage)
            holder.beerName.text = item.name
            holder.beerTagline.text = item.tagline
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_beer, parent, false)
        return ViewHolder(view)
    }
}