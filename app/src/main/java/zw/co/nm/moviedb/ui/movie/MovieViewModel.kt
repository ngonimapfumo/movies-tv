package zw.co.nm.moviedb.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import zw.co.nm.moviedb.models.network.GetCreditsResponse
import zw.co.nm.moviedb.models.network.GetMovieDetailResponse
import zw.co.nm.moviedb.models.network.GetSimilarMoviesResponse
import zw.co.nm.moviedb.models.network.SearchMovieResponse
import zw.co.nm.moviedb.network.Response
import zw.co.nm.moviedb.repositories.MoviesRepository


class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val moviesRepository = MoviesRepository()

    fun getMovieDetail(movieId: Int): Flow<Response<GetMovieDetailResponse>> {
        return moviesRepository.getMovieDetails(movieId)
    }

    fun getSimilarMoviesList(movieId: Int): Flow<Response<GetSimilarMoviesResponse>> {
        return moviesRepository.getSimilarMoviesList(movieId)
    }
    fun getCredits(movieId: Int):Flow<Response<GetCreditsResponse>>{
        return moviesRepository.getCredits(movieId)
    }

    fun searchMovie(query:String):Flow<Response<SearchMovieResponse>>{
        return moviesRepository.searchMovie(query)
    }


}