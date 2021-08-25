package com.nimadugarov.testappsequenia.presentation.home.list.viewholder

import android.view.View
import com.nimadugarov.testappsequenia.R
import com.nimadugarov.testappsequenia.databinding.HeaderItemBinding
import com.nimadugarov.testappsequenia.presentation.home.list.AdapterClickListener
import com.nimadugarov.testappsequenia.presentation.home.list.ViewHolderVisitor
import com.nimadugarov.testappsequenia.presentation.model.HeaderItemViewModel

class HeaderItemViewHolder : ViewHolderVisitor {

    override val layout: Int = R.layout.header_item

    override fun acceptBinding(item: Any): Boolean = item is HeaderItemViewModel

    override fun bind(itemView: View, item: Any, clickListener: AdapterClickListener, position: Int) {
        val binding = HeaderItemBinding.bind(itemView)
        binding.header.text = (item as HeaderItemViewModel).header
    }
}