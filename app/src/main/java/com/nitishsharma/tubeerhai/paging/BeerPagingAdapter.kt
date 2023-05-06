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
import com.nitishsharma.tubeerhai.databinding.FragmentHomeBinding

class BeerPagingAdapter(
    private val clickListeners: ClickListeners,
    private val isLoading: Boolean,
    private val fragement: FragmentHomeBinding
) : PagingDataAdapter<Beer, BeerPagingAdapter.ViewHolder>(COMPARATOR) {

    //click listeners
    interface ClickListeners {
        fun onItemLongClickListener(item: Beer)
        fun onWhatsappClickListener(item: Beer)
    }

    //getting views data
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beerName = itemView.findViewById<TextView>(R.id.beerName)
        val beerTagline = itemView.findViewById<TextView>(R.id.tagLine)
        val beerImage = itemView.findViewById<ImageView>(R.id.beerImage)
        val whatsappShare = itemView.findViewById<ImageView>(R.id.shareWhatsappIv)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(
                oldItem: Beer,
                newItem: Beer
            ): Boolean { //checking if content is same using id of beer
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Beer,
                newItem: Beer
            ): Boolean { // returning if the values are same or not
                return oldItem == newItem
            }
        }
    }

    //binding views with data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (isLoading) // if is loading
            fragement.progressBar.visibility = View.GONE //set to gone
        if (item != null) {
            with(holder) {
                Glide.with(beerImage.context).load(item.image_url)
                    .diskCacheStrategy( //set image with caching
                        DiskCacheStrategy.DATA
                    ).into(beerImage)
                beerName.text = item.name
                beerTagline.text = item.tagline

                whatsappShare.setOnClickListener { //click on whatsapp share to share beer
                    clickListeners.onWhatsappClickListener(item)
                }
                itemView.setOnLongClickListener {// long tap to view details of beer
                    clickListeners.onItemLongClickListener(item)
                    true
                }
            }
        }
    }

    //onCreateVH
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_beer, parent, false)
        return ViewHolder(view)
    }
}