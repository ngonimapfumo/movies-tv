package zw.co.nm.moviedb.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import zw.co.nm.moviedb.model.GetCreditsResponse
import zw.co.nm.moviedb.model.GetMovieDetailResponse
import zw.co.nm.moviedb.model.GetSimilarMoviesResponse
import zw.co.nm.moviedb.model.SearchMovieResponse
import zw.co.nm.moviedb.data.api.Response
import zw.co.nm.moviedb.model.SearchMultiResponse
import zw.co.nm.moviedb.repo.MoviesRepository


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
    fun searchMulti(query:String):Flow<Response<SearchMultiResponse>>{
        return moviesRepository.searchMulti(query)
    }


}