package zw.co.nm.moviedb.presentation.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetPopularMoviesListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetPopularTVSeriesListResponse
import zw.co.nm.moviedb.databinding.FragmentWatchlistPageBinding
import zw.co.nm.moviedb.presentation.auth.AuthViewModel
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.EndlessScrollListener
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.GeneralUtil.generalAlertDialog

class WatchlistPageFragment : Fragment() {

    private var _binding: FragmentWatchlistPageBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by activityViewModels()

    private lateinit var moviesAdapter: WatchlistAdapter
    private lateinit var tvAdapter: WatchlistTvAdapter

    private var mediaType: String = Constants.MEDIA_TYPE_MOVIE
    private var isLoadingMore = false
    private var isLastPage = false
    private var hasLoadedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaType = arguments?.getString(ARG_MEDIA_TYPE) ?: Constants.MEDIA_TYPE_MOVIE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchlistPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpList()
        observe()
    }

    override fun onResume() {
        super.onResume()
        loadInitial()
    }

    private fun setUpList() {
        if (mediaType == Constants.MEDIA_TYPE_TV) {
            tvAdapter = WatchlistTvAdapter { show -> confirmRemoveTv(show) }
            binding.recyclerView.adapter = tvAdapter
        } else {
            moviesAdapter = WatchlistAdapter { movie -> confirmRemoveMovie(movie) }
            binding.recyclerView.adapter = moviesAdapter
        }

        val layoutManager = binding.recyclerView.layoutManager as GridLayoutManager
        binding.recyclerView.addOnScrollListener(
            EndlessScrollListener(layoutManager) { loadNextPage() }
        )
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val shouldShow = recyclerView.computeVerticalScrollOffset() > 800
                binding.scrollTopFab.visibility = if (shouldShow) VISIBLE else GONE
            }
        })
        binding.scrollTopFab.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }
    }

    private fun observe() {
        if (mediaType == Constants.MEDIA_TYPE_TV) {
            authViewModel.watchlistTv.observe(viewLifecycleOwner) { response ->
                handleTvResponse(response)
            }
        } else {
            authViewModel.watchlistMovies.observe(viewLifecycleOwner) { response ->
                handleMoviesResponse(response)
            }
        }

        authViewModel.watchlistMutation.observe(viewLifecycleOwner) { mutation ->
            if (!mutation.success) {
                Toast.makeText(requireContext(), R.string.watchlist_error, Toast.LENGTH_SHORT).show()
                return@observe
            }
            if (!mutation.added && mutation.mediaType == mediaType) {
                Toast.makeText(requireContext(), R.string.removed_from_watchlist, Toast.LENGTH_SHORT)
                    .show()
                loadInitial()
            }
        }
    }

    private fun handleMoviesResponse(
        response: zw.co.nm.moviedb.data.remote.util.Response<GetPopularMoviesListResponse>
    ) {
        binding.progressBar.visibility = GONE
        setLoadMoreVisible(false)
        val loadingMore = isLoadingMore
        isLoadingMore = false

        when (response.data) {
            null -> {
                if (loadingMore && authViewModel.moviesPage > 1) {
                    authViewModel.moviesPage--
                }
                actionSnack(binding.root, getString(R.string.watchlist_load_error), "Retry") {
                    if (authViewModel.moviesPage == 1) loadInitial() else {
                        isLoadingMore = false
                        loadNextPage()
                    }
                }
            }

            else -> {
                hasLoadedOnce = true
                isLastPage = response.body.page >= response.body.totalPages
                if (response.body.page == 1) {
                    moviesAdapter.submitList(response.body.results)
                    if (response.body.results.isEmpty()) {
                        Toast.makeText(requireContext(), R.string.watchlist_empty, Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    moviesAdapter.appendList(response.body.results)
                }
            }
        }
    }

    private fun handleTvResponse(
        response: zw.co.nm.moviedb.data.remote.util.Response<GetPopularTVSeriesListResponse>
    ) {
        binding.progressBar.visibility = GONE
        setLoadMoreVisible(false)
        val loadingMore = isLoadingMore
        isLoadingMore = false

        when (response.data) {
            null -> {
                if (loadingMore && authViewModel.tvPage > 1) {
                    authViewModel.tvPage--
                }
                actionSnack(binding.root, getString(R.string.watchlist_load_error), "Retry") {
                    if (authViewModel.tvPage == 1) loadInitial() else {
                        isLoadingMore = false
                        loadNextPage()
                    }
                }
            }

            else -> {
                hasLoadedOnce = true
                isLastPage = response.body.page >= response.body.totalPages
                if (response.body.page == 1) {
                    tvAdapter.submitList(response.body.results)
                    if (response.body.results.isEmpty()) {
                        Toast.makeText(requireContext(), R.string.watchlist_empty_tv, Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    tvAdapter.appendList(response.body.results)
                }
            }
        }
    }

    private fun confirmRemoveMovie(movie: GetPopularMoviesListResponse.Result) {
        generalAlertDialog(
            requireContext(),
            getString(R.string.remove_from_watchlist_title),
            getString(R.string.remove_from_watchlist_message, movie.title),
            getString(R.string.remove),
            getString(R.string.cancel),
            { _, _ -> authViewModel.removeMovieFromWatchlist(movie.id) },
            null
        )
    }

    private fun confirmRemoveTv(show: GetPopularTVSeriesListResponse.Result) {
        generalAlertDialog(
            requireContext(),
            getString(R.string.remove_from_watchlist_title),
            getString(R.string.remove_from_watchlist_message, show.name),
            getString(R.string.remove),
            getString(R.string.cancel),
            { _, _ -> authViewModel.removeTvFromWatchlist(show.id) },
            null
        )
    }

    private fun loadInitial() {
        isLastPage = false
        isLoadingMore = false
        if (!hasLoadedOnce) {
            binding.progressBar.visibility = VISIBLE
        }
        setLoadMoreVisible(false)
        if (mediaType == Constants.MEDIA_TYPE_TV) {
            authViewModel.tvPage = 1
            authViewModel.getWatchlistTv()
        } else {
            authViewModel.moviesPage = 1
            authViewModel.getWatchlistMovies()
        }
    }

    private fun loadNextPage() {
        if (isLoadingMore || isLastPage) return
        isLoadingMore = true
        setLoadMoreVisible(true)
        if (mediaType == Constants.MEDIA_TYPE_TV) {
            authViewModel.tvPage++
            authViewModel.getWatchlistTv()
        } else {
            authViewModel.moviesPage++
            authViewModel.getWatchlistMovies()
        }
    }

    private fun setLoadMoreVisible(visible: Boolean) {
        binding.loadMoreCard.visibility = if (visible) VISIBLE else GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_MEDIA_TYPE = "media_type"

        fun newInstance(mediaType: String): WatchlistPageFragment {
            return WatchlistPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_MEDIA_TYPE, mediaType)
                }
            }
        }
    }
}
