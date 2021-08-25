package com.nimadugarov.testappsequenia.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nimadugarov.testappsequenia.R
import com.nimadugarov.testappsequenia.databinding.FragmentHomeBinding
import com.nimadugarov.testappsequenia.presentation.home.list.AdapterClickListener
import com.nimadugarov.testappsequenia.presentation.home.list.HomeAdapter
import com.nimadugarov.testappsequenia.presentation.home.list.ItemTypes
import com.nimadugarov.testappsequenia.presentation.home.list.ViewHoldersManager
import com.nimadugarov.testappsequenia.presentation.model.GenreItemViewModel
import com.nimadugarov.testappsequenia.presentation.model.ItemViewModel
import com.nimadugarov.testappsequenia.presentation.model.MovieItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class HomeFragment : MvpAppCompatFragment(R.layout.fragment_home), HomeView {

    @Inject
    lateinit var presenterProvider: Provider<HomePresenter>
    private val presenter: HomePresenter by moxyPresenter { presenterProvider.get() }

    @Inject
    lateinit var viewHoldersManager: ViewHoldersManager

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeAdapter
        get() = HomeAdapter(viewHoldersManager, AdapterClickListener { item, position ->
            if (item is MovieItemViewModel) { presenter.onMovieClicked(item) }
            if (item is GenreItemViewModel) { presenter.onGenreClicked(item, position) }
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        observeState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun submitItems(items: List<ItemViewModel>) {
        (binding.recyclerviewMovies.adapter as HomeAdapter).submitList(items)
    }

    override fun selectGenreItem(position: Int) {
        (binding.recyclerviewMovies.adapter as HomeAdapter).selectGenreItem(position)
    }

    override fun unselectGenreItem(position: Int) {
        (binding.recyclerviewMovies.adapter as HomeAdapter).unselectGenreItem(position)
    }

    override fun navigateToDetails(movieItemViewModel: MovieItemViewModel) {
        val action = HomeFragmentDirections.actionHomeToDetails(movieItemViewModel.movieItem)
        findNavController().navigate(action)
    }

    private fun initViews() {
        val manager = GridLayoutManager(context, resources.getInteger(R.integer.grid_column_count))
        binding.recyclerviewMovies.apply {
            adapter = homeAdapter
            layoutManager = manager
        }
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when {
                    binding.recyclerviewMovies
                        .adapter?.getItemViewType(position) == ItemTypes.TYPE_GENRE -> 1
                    binding.recyclerviewMovies
                        .adapter?.getItemViewType(position) == ItemTypes.TYPE_HEADER -> resources.getInteger(R.integer.grid_column_count)
                    else -> 1
                }
            }
        }
    }

    private fun initListeners() {
        binding.buttonRefresh.setOnClickListener {
            presenter.onRefreshClicked()
        }
    }

    private fun observeState() {
        presenter.state.observe(viewLifecycleOwner) {
            binding.progress.isVisible = (it is State.Loading)
            binding.recyclerviewMovies.isVisible = (it is State.Success)
            binding.error.isVisible = (it is State.Error)
        }
    }
}