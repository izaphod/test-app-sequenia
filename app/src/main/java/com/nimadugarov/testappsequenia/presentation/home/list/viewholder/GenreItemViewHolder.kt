package com.nimadugarov.testappsequenia.presentation.home.list.viewholder

import android.view.View
import com.nimadugarov.testappsequenia.R
import com.nimadugarov.testappsequenia.databinding.GenreItemBinding
import com.nimadugarov.testappsequenia.domain.model.Genre
import com.nimadugarov.testappsequenia.presentation.home.list.AdapterClickListener
import com.nimadugarov.testappsequenia.presentation.home.list.ViewHolderVisitor
import com.nimadugarov.testappsequenia.presentation.model.GenreItemViewModel

class GenreItemViewHolder : ViewHolderVisitor {

    override val layout: Int = R.layout.genre_item

    override fun acceptBinding(item: Any): Boolean = item is GenreItemViewModel

    override fun bind(itemView: View, item: Any, clickListener: AdapterClickListener, position: Int) {
        val binding = GenreItemBinding.bind(itemView)
        val viewModel = item as GenreItemViewModel
        when (viewModel.genre) {
            Genre.DRAMA -> binding.genre.text = itemView.resources.getText(R.string.genre_drama)
            Genre.FANTASY -> binding.genre.text = itemView.resources.getText(R.string.genre_fantasy)
            Genre.CRIME -> binding.genre.text = itemView.resources.getText(R.string.genre_crime)
            Genre.DETECTIVE -> binding.genre.text = itemView.resources.getText(R.string.genre_detective)
            Genre.MELODRAMA -> binding.genre.text = itemView.resources.getText(R.string.genre_melodrama)
            Genre.BIOGRAPHY -> binding.genre.text = itemView.resources.getText(R.string.genre_biography)
            Genre.COMEDY -> binding.genre.text = itemView.resources.getText(R.string.genre_comedy)
            Genre.ACTION -> binding.genre.text = itemView.resources.getText(R.string.genre_action)
            Genre.MUSICAL -> binding.genre.text = itemView.resources.getText(R.string.genre_musical)
            Genre.ADVENTURE -> binding.genre.text = itemView.resources.getText(R.string.genre_adventure)
            Genre.HORROR -> binding.genre.text = itemView.resources.getText(R.string.genre_horror)
        }

        binding.root.isActivated = viewModel.isSelected

        itemView.setOnClickListener(clickListener.onClick(viewModel, position))
    }
}