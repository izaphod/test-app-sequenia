package com.nimadugarov.testappsequenia.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.request.RequestOptions
import com.nimadugarov.testappsequenia.R
import com.nimadugarov.testappsequenia.databinding.FragmentDetailsBinding
import com.nimadugarov.testappsequenia.di.GlideApp
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

@AndroidEntryPoint
class DetailsFragment : MvpAppCompatFragment(R.layout.fragment_details), DetailsView {

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    @ProvidePresenter
    fun provideDetailsPresenter(): DetailsPresenter {
        val movieItem = args.movieItem
        return DetailsPresenter(movieItem)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.movieItem = args.movieItem
        binding.toolbarDetails.setupWithNavController(findNavController())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setToolbarTitle(title: String) {
        binding.toolbarDetails.title = title
    }

    override fun setMovieDetails(
        name: String,
        year: String,
        rating: Double?,
        description: String?
    ) {
        var movieRating = getString(R.string.no_rating)
        var movieDescription = getString(R.string.no_description)
        rating?.let { movieRating = getString(R.string.movie_rating, it.toString()) }
        description?.let {
            if (it.isNotBlank()) {
                movieDescription = it
            }
        }
        binding.movieName.text = name
        binding.movieYear.text = getString(R.string.movie_year, year)
        binding.movieRating.text = movieRating
        binding.movieDescription.text = movieDescription
    }

    override fun setMoviePoster(posterUrl: String?) {
        GlideApp.with(this)
            .load(posterUrl)
            .apply(
                RequestOptions()
                    .placeholder(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.loading_animation
                        )
                    )
                    .error(ContextCompat.getDrawable(requireContext(), R.drawable.ic_broken_image))
                    .centerInside()
            )
            .centerCrop()
            .into(binding.moviePoster)
    }
}