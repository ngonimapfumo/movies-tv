package zw.co.nm.moviedb.presentation.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.NN

class newViewModel : ViewModel() {

    var moviesRepo = MoviesRepo()
    var movieDetail: NN by mutableStateOf(NN())
    fun getMovieDetail(id: Int, lang: String) {
        viewModelScope.launch {
            movieDetail = moviesRepo.getNN(id, lang)
        }
    }

}