package zw.co.nm.moviedb.presentation.search

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.SearchMultiResponse

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val searchRepo = SearchRepo(application)
    var page: Int = 1

    private val _searchMulti =
        MutableLiveData<Response<SearchMultiResponse>>()
    val searchMulti: LiveData<Response<SearchMultiResponse>> =
        _searchMulti

    fun searchMulti(query: String) {
        viewModelScope.launch {
            val data = searchRepo.searchMulti(query, page)
            if (data.isSuccessful) {
                _searchMulti.value = searchRepo.searchMulti(query, page)
            } else {
                Toast.makeText(
                    getApplication(),
                    "Something went wrong, please try again later",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun resetPages() {
        page = 1
    }
}