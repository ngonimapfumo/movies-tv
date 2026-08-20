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

    private var listType: String = Constants.LIST_TYPE_WATCHLIST
    private var mediaType: String = Constants.MEDIA_TYPE_MOVIE
    private var isLoadingMore = false
    private var isLastPage = false
    private var hasLoadedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listType = arguments?.getString(ARG_LIST_TYPE) ?: Constants.LIST_TYPE_WATCHLIST
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
            when (listType) {
                Constants.LIST_TYPE_FAVORITES ->
                    authViewModel.favoriteTv.observe(viewLifecycleOwner) { handleTvResponse(it) }

                Constants.LIST_TYPE_RATED ->
                    authViewModel.ratedTv.observe(viewLifecycleOwner) { handleTvResponse(it) }

                else ->
                    authViewModel.watchlistTv.observe(viewLifecycleOwner) { handleTvResponse(it) }
            }
        } else {
            when (listType) {
                Constants.LIST_TYPE_FAVORITES ->
                    authViewModel.favoriteMovies.observe(viewLifecycleOwner) {
                        handleMoviesResponse(it)
                    }

                Constants.LIST_TYPE_RATED ->
                    authViewModel.ratedMovies.observe(viewLifecycleOwner) {
                        handleMoviesResponse(it)
                    }

                else ->
                    authViewModel.watchlistMovies.observe(viewLifecycleOwner) {
                        handleMoviesResponse(it)
                    }
            }
        }

        authViewModel.watchlistMutation.observe(viewLifecycleOwner) { mutation ->
            if (listType != Constants.LIST_TYPE_WATCHLIST) return@observe
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

        authViewModel.favoriteMutation.observe(viewLifecycleOwner) { mutation ->
            if (listType != Constants.LIST_TYPE_FAVORITES) return@observe
            if (!mutation.success) {
                Toast.makeText(requireContext(), R.string.favorites_error, Toast.LENGTH_SHORT).show()
                return@observe
            }
            if (!mutation.added && mutation.mediaType == mediaType) {
                Toast.makeText(requireContext(), R.string.removed_from_favorites, Toast.LENGTH_SHORT)
                    .show()
                loadInitial()
            }
        }

        authViewModel.ratingMutation.observe(viewLifecycleOwner) { mutation ->
            if (listType != Constants.LIST_TYPE_RATED) return@observe
            if (!mutation.success) {
                Toast.makeText(requireContext(), R.string.rating_error, Toast.LENGTH_SHORT).show()
                return@observe
            }
            if (mutation.deleted && mutation.mediaType == mediaType) {
                Toast.makeText(requireContext(), R.string.rating_removed, Toast.LENGTH_SHORT).show()
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
                if (loadingMore && moviesPage() > 1) {
                    decrementMoviesPage()
                }
                actionSnack(binding.root, getString(loadErrorRes()), "Retry") {
                    if (moviesPage() == 1) loadInitial() else {
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
                        Toast.makeText(requireContext(), emptyMoviesRes(), Toast.LENGTH_SHORT)
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
                if (loadingMore && tvPage() > 1) {
                    decrementTvPage()
                }
                actionSnack(binding.root, getString(loadErrorRes()), "Retry") {
                    if (tvPage() == 1) loadInitial() else {
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
                        Toast.makeText(requireContext(), emptyTvRes(), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    tvAdapter.appendList(response.body.results)
                }
            }
        }
    }

    private fun confirmRemoveMovie(movie: GetPopularMoviesListResponse.Result) {
        val title = when (listType) {
            Constants.LIST_TYPE_FAVORITES -> getString(R.string.remove_from_favorites_title)
            Constants.LIST_TYPE_RATED -> getString(R.string.remove_rating_title)
            else -> getString(R.string.remove_from_watchlist_title)
        }
        val message = when (listType) {
            Constants.LIST_TYPE_FAVORITES ->
                getString(R.string.remove_from_favorites_message, movie.title)

            Constants.LIST_TYPE_RATED ->
                getString(R.string.remove_rating_message, movie.title)

            else -> getString(R.string.remove_from_watchlist_message, movie.title)
        }
        generalAlertDialog(
            requireContext(),
            title,
            message,
            getString(R.string.remove),
            getString(R.string.cancel),
            { _, _ ->
                when (listType) {
                    Constants.LIST_TYPE_FAVORITES ->
                        authViewModel.removeMovieFromFavorites(movie.id)

                    Constants.LIST_TYPE_RATED ->
                        authViewModel.removeMovieRating(movie.id)

                    else -> authViewModel.removeMovieFromWatchlist(movie.id)
                }
            },
            null
        )
    }

    private fun confirmRemoveTv(show: GetPopularTVSeriesListResponse.Result) {
        val title = when (listType) {
            Constants.LIST_TYPE_FAVORITES -> getString(R.string.remove_from_favorites_title)
            Constants.LIST_TYPE_RATED -> getString(R.string.remove_rating_title)
            else -> getString(R.string.remove_from_watchlist_title)
        }
        val message = when (listType) {
            Constants.LIST_TYPE_FAVORITES ->
                getString(R.string.remove_from_favorites_message, show.name)

            Constants.LIST_TYPE_RATED ->
                getString(R.string.remove_rating_message, show.name)

            else -> getString(R.string.remove_from_watchlist_message, show.name)
        }
        generalAlertDialog(
            requireContext(),
            title,
            message,
            getString(R.string.remove),
            getString(R.string.cancel),
            { _, _ ->
                when (listType) {
                    Constants.LIST_TYPE_FAVORITES ->
                        authViewModel.removeTvFromFavorites(show.id)

                    Constants.LIST_TYPE_RATED ->
                        authViewModel.removeTvRating(show.id)

                    else -> authViewModel.removeTvFromWatchlist(show.id)
                }
            },
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
            setTvPage(1)
            fetchTv()
        } else {
            setMoviesPage(1)
            fetchMovies()
        }
    }

    private fun loadNextPage() {
        if (isLoadingMore || isLastPage) return
        isLoadingMore = true
        setLoadMoreVisible(true)
        if (mediaType == Constants.MEDIA_TYPE_TV) {
            setTvPage(tvPage() + 1)
            fetchTv()
        } else {
            setMoviesPage(moviesPage() + 1)
            fetchMovies()
        }
    }

    private fun fetchMovies() {
        when (listType) {
            Constants.LIST_TYPE_FAVORITES -> authViewModel.getFavoriteMovies()
            Constants.LIST_TYPE_RATED -> authViewModel.getRatedMovies()
            else -> authViewModel.getWatchlistMovies()
        }
    }

    private fun fetchTv() {
        when (listType) {
            Constants.LIST_TYPE_FAVORITES -> authViewModel.getFavoriteTv()
            Constants.LIST_TYPE_RATED -> authViewModel.getRatedTv()
            else -> authViewModel.getWatchlistTv()
        }
    }

    private fun moviesPage(): Int = when (listType) {
        Constants.LIST_TYPE_FAVORITES -> authViewModel.favoriteMoviesPage
        Constants.LIST_TYPE_RATED -> authViewModel.ratedMoviesPage
        else -> authViewModel.moviesPage
    }

    private fun tvPage(): Int = when (listType) {
        Constants.LIST_TYPE_FAVORITES -> authViewModel.favoriteTvPage
        Constants.LIST_TYPE_RATED -> authViewModel.ratedTvPage
        else -> authViewModel.tvPage
    }

    private fun setMoviesPage(page: Int) {
        when (listType) {
            Constants.LIST_TYPE_FAVORITES -> authViewModel.favoriteMoviesPage = page
            Constants.LIST_TYPE_RATED -> authViewModel.ratedMoviesPage = page
            else -> authViewModel.moviesPage = page
        }
    }

    private fun setTvPage(page: Int) {
        when (listType) {
            Constants.LIST_TYPE_FAVORITES -> authViewModel.favoriteTvPage = page
            Constants.LIST_TYPE_RATED -> authViewModel.ratedTvPage = page
            else -> authViewModel.tvPage = page
        }
    }

    private fun decrementMoviesPage() {
        setMoviesPage(moviesPage() - 1)
    }

    private fun decrementTvPage() {
        setTvPage(tvPage() - 1)
    }

    private fun emptyMoviesRes(): Int = when (listType) {
        Constants.LIST_TYPE_FAVORITES -> R.string.favorites_empty
        Constants.LIST_TYPE_RATED -> R.string.rated_empty
        else -> R.string.watchlist_empty
    }

    private fun emptyTvRes(): Int = when (listType) {
        Constants.LIST_TYPE_FAVORITES -> R.string.favorites_empty_tv
        Constants.LIST_TYPE_RATED -> R.string.rated_empty_tv
        else -> R.string.watchlist_empty_tv
    }

    private fun loadErrorRes(): Int = when (listType) {
        Constants.LIST_TYPE_FAVORITES -> R.string.favorites_load_error
        Constants.LIST_TYPE_RATED -> R.string.rated_load_error
        else -> R.string.watchlist_load_error
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
        private const val ARG_LIST_TYPE = "list_type"

        fun newInstance(listType: String, mediaType: String): WatchlistPageFragment {
            return WatchlistPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LIST_TYPE, listType)
                    putString(ARG_MEDIA_TYPE, mediaType)
                }
            }
        }
    }
}
