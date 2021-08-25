package com.nimadugarov.testappsequenia.presentation.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nimadugarov.testappsequenia.presentation.model.GenreItemViewModel
import com.nimadugarov.testappsequenia.presentation.model.ItemViewModel

class HomeAdapter (
    private val viewHoldersManager: ViewHoldersManager,
    private val clickListener: AdapterClickListener
) : ListAdapter<ItemViewModel, HomeAdapter.HomeViewHolder>(HomeDiffCallback()) {

    inner class HomeViewHolder(
        private val itemView: View,
        private val holder: ViewHolderVisitor
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ItemViewModel, position: Int) = holder.bind(itemView, item, clickListener, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        LayoutInflater.from(parent.context).run {
            val holder = viewHoldersManager.getViewHolder(viewType)
            HomeViewHolder(this.inflate(holder.layout, parent, false), holder)
        }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun getItemViewType(position: Int): Int =
        viewHoldersManager.getItemType(getItem(position))

    fun selectGenreItem(position: Int) {
        if (currentList.isNotEmpty()) {
            (currentList[position] as GenreItemViewModel).isSelected = true
            notifyItemChanged(position)
        }
    }

    fun unselectGenreItem(position: Int) {
        if (currentList.isNotEmpty()) {
            (currentList[position] as GenreItemViewModel).isSelected = false
            notifyItemChanged(position)
        }
    }
}

class HomeDiffCallback : DiffUtil.ItemCallback<ItemViewModel>() {
    override fun areItemsTheSame(oldItem: ItemViewModel, newItem: ItemViewModel): Boolean =
        oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: ItemViewModel, newItem: ItemViewModel): Boolean =
        oldItem == newItem
}
