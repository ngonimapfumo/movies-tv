package zw.co.nm.moviedb.presentation.lists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.CreateListResponse
import zw.co.nm.moviedb.data.remote.model.response.GetAccountListsResponse
import zw.co.nm.moviedb.data.remote.model.response.GetListDetailResponse
import zw.co.nm.moviedb.data.remote.model.response.StatusResponse
import zw.co.nm.moviedb.data.remote.util.Response
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.Constants.ACCOUNT_ID
import zw.co.nm.moviedb.util.Constants.LANGUAGE_KEY
import zw.co.nm.moviedb.util.Constants.SESSION_ID

data class ListMutation(
    val success: Boolean,
    val type: Type,
    val message: String? = null
) {
    enum class Type { ADD, REMOVE, DELETE }
}

class ListsViewModel(application: Application) : AndroidViewModel(application) {

    private val listsRepo = ListsRepo()
    private var language = ConfigStore.getStringLang(application, LANGUAGE_KEY) ?: "en-US"

    var page: Int = 1

    private val _accountLists = MutableLiveData<Response<GetAccountListsResponse>>()
    val accountLists: LiveData<Response<GetAccountListsResponse>> = _accountLists

    private val _listDetail = MutableLiveData<Response<GetListDetailResponse>>()
    val listDetail: LiveData<Response<GetListDetailResponse>> = _listDetail

    private val _createResult = MutableLiveData<Response<CreateListResponse>>()
    val createResult: LiveData<Response<CreateListResponse>> = _createResult

    private val _mutation = MutableLiveData<ListMutation>()
    val mutation: LiveData<ListMutation> = _mutation

    fun getAccountLists() {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID)
            val accountId = ConfigStore.getInt(getApplication(), ACCOUNT_ID)
            if (sessionId.isNullOrBlank() || accountId == 0) {
                _accountLists.value = Response.failure(IllegalStateException("Not logged in"))
                return@launch
            }
            _accountLists.value = listsRepo.getAccountLists(
                accountId = accountId,
                sessionId = sessionId,
                language = language,
                page = page
            )
        }
    }

    fun createList(name: String, description: String) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID) ?: return@launch
            _createResult.value = listsRepo.createList(
                sessionId = sessionId,
                name = name,
                description = description,
                language = language.take(2)
            )
        }
    }

    fun getListDetail(listId: Int) {
        viewModelScope.launch {
            _listDetail.value = listsRepo.getListDetail(listId, language)
        }
    }

    fun addMovieToList(listId: Int, movieId: Int) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID) ?: return@launch
            val response = listsRepo.addItem(listId, sessionId, movieId)
            _mutation.value = ListMutation(
                success = response.isSuccessful && response.body.success,
                type = ListMutation.Type.ADD,
                message = runCatching { response.body.statusMessage }.getOrNull()
            )
        }
    }

    fun removeMovieFromList(listId: Int, movieId: Int) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID) ?: return@launch
            val response = listsRepo.removeItem(listId, sessionId, movieId)
            _mutation.value = ListMutation(
                success = response.isSuccessful && response.body.success,
                type = ListMutation.Type.REMOVE,
                message = runCatching { response.body.statusMessage }.getOrNull()
            )
        }
    }

    fun deleteList(listId: Int) {
        viewModelScope.launch {
            val sessionId = ConfigStore.getString(getApplication(), SESSION_ID) ?: return@launch
            val response = listsRepo.deleteList(listId, sessionId)
            _mutation.value = ListMutation(
                success = response.isSuccessful && response.body.success,
                type = ListMutation.Type.DELETE,
                message = runCatching { response.body.statusMessage }.getOrNull()
            )
        }
    }
}
