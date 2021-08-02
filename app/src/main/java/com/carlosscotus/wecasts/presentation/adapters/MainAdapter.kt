package com.carlosscotus.wecasts.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.carlosscotus.engine.model.Item
import com.carlosscotus.engine.model.Podcast
import com.carlosscotus.wecasts.R
import com.carlosscotus.wecasts.presentation.fragments.HeaderFragment

class MainAdapter(
    private val podcasts: MutableList<Podcast>,
    private val activity: FragmentActivity
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val HEADER_TYPE = 0
    private val ITEM_TYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == HEADER_TYPE) {
            ViewPagerViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.viewpager_item_list,
                    parent,
                    false
                )
            )
        } else {
            ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recyclerview_item,
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(podcasts, position)
        }

        when (holder) {
            is ItemViewHolder -> {
                holder.bind(podcasts, position)
            }

            is ViewPagerViewHolder -> {
                holder.bind(podcasts, position)
            }
        }
    }

    override fun getItemCount() = podcasts.size

    override fun getItemViewType(position: Int): Int {
        return if (position == HEADER_TYPE) {
            HEADER_TYPE
        } else {
            ITEM_TYPE
        }
    }

    inner class ItemViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemAdapter = ItemAdapter()

        fun bind(podcasts: MutableList<Podcast>, position: Int) {
            val podcast = podcasts[position]
            with(itemView.findViewById<RecyclerView>(R.id.recyclerview)) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    itemView.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = itemAdapter
                itemAdapter.addAll(podcast.casts)
            }

            itemView.findViewById<TextView>(R.id.title).text = podcast.title
        }
    }

    inner class ViewPagerViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(podcasts: MutableList<Podcast>, position: Int) {
            val podcast = podcasts[position]
            with(itemView.findViewById<ViewPager2>(R.id.viewPager)) {
                adapter = ViewPagerAdapter(activity, podcast.casts)
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 2
                setPageTransformer(MarginPageTransformer(10))
            }
        }
    }
}

class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val casts: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PodcastViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_list,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PodcastViewHolder) {
            holder.bind(casts[position])
        }
    }

    override fun getItemCount() = casts.size

    fun addAll(casts: MutableList<Item>) {
        this.casts.clear()
        this.casts.addAll(casts)
    }

    inner class PodcastViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val subtitle = itemView.findViewById<TextView>(R.id.subtitle)
        val image = itemView.findViewById<ImageView>(R.id.img)

        fun bind(item: Item) {
            title.text = item.title
            subtitle.text = item.subTitle

            Glide.with(itemView.context).load(item.image).centerCrop().into(image)
        }
    }
}

class ViewPagerAdapter(
    context: FragmentActivity,
    private val casts: MutableList<Item>
) : FragmentStateAdapter(context) {
    override fun getItemCount() = casts.size
    override fun createFragment(position: Int) = HeaderFragment.newInstance(casts[position])
}