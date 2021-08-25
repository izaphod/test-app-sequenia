package com.nimadugarov.testappsequenia.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
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
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class DetailsFragment : MvpAppCompatFragment(R.layout.fragment_details), DetailsView {

    @Inject
    lateinit var presenterProvider: Provider<DetailsPresenter>
    private val presenter: DetailsPresenter by moxyPresenter { presenterProvider.get() }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

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
        binding.toolbarDetails.setupWithNavController(findNavController())
        binding.toolbarDetails.title = args.movieItem.localizedName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showMovieDetails() {
        var rating = getString(R.string.no_rating)
        if (args.movieItem.rating != null) {
            rating = getString(R.string.movie_rating, args.movieItem.rating.toString())
        }
        GlideApp.with(this)
            .load(args.movieItem.posterUrl)
            .apply(
                RequestOptions()
                    .placeholder(ContextCompat.getDrawable(requireContext(), R.drawable.loading_animation))
                    .error(ContextCompat.getDrawable(requireContext(), R.drawable.ic_broken_image))
                    .centerInside()
            )
            .centerCrop()
            .into(binding.moviePoster)
        binding.movieName.text = args.movieItem.name
        binding.movieYear.text = getString(R.string.movie_year, args.movieItem.year.toString())
        binding.movieRating.text = rating
        if (args.movieItem.description.isNullOrBlank()) {
            binding.movieDescription.text = getString(R.string.no_description)
        } else {
            binding.movieDescription.text = args.movieItem.description
        }

    }
}