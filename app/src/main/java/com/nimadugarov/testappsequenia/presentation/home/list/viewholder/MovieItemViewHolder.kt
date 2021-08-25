package com.nimadugarov.testappsequenia.presentation.home.list.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.request.RequestOptions
import com.nimadugarov.testappsequenia.R
import com.nimadugarov.testappsequenia.databinding.MovieItemBinding
import com.nimadugarov.testappsequenia.di.GlideApp
import com.nimadugarov.testappsequenia.presentation.home.list.AdapterClickListener
import com.nimadugarov.testappsequenia.presentation.home.list.ViewHolderVisitor
import com.nimadugarov.testappsequenia.presentation.model.MovieItemViewModel

class MovieItemViewHolder : ViewHolderVisitor {

    override val layout: Int = R.layout.movie_item

    override fun acceptBinding(item: Any): Boolean = item is MovieItemViewModel

    override fun bind(itemView: View, item: Any, clickListener: AdapterClickListener, position: Int) {
        val binding = MovieItemBinding.bind(itemView)
        val viewModel = item as MovieItemViewModel
        binding.movieLocalizedName.text = viewModel.movieItem.localizedName
        GlideApp.with(itemView)
            .load(viewModel.movieItem.posterUrl)
            .apply(
                RequestOptions()
                    .placeholder(ContextCompat.getDrawable(itemView.context, R.drawable.loading_animation))
                    .error(ContextCompat.getDrawable(itemView.context, R.drawable.ic_broken_image))
                    .centerInside()
            )
            .centerCrop()
            .into(binding.moviePoster)
        itemView.setOnClickListener(clickListener.onClick(viewModel, position))
    }
}