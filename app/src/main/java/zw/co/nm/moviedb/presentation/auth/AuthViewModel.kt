package zw.co.nm.moviedb.presentation.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.CreateRequestTokenResponse
import zw.co.nm.moviedb.data.remote.model.response.CreateSessionResponse
import zw.co.nm.moviedb.data.remote.util.Response

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepo = AuthRepo()

    private val _getLoginSession = MutableLiveData<Response<CreateSessionResponse>>()
    val getLoginSession: LiveData<Response<CreateSessionResponse>> = _getLoginSession

    private val _getRequestToken = MutableLiveData<Response<CreateRequestTokenResponse>>()
    val getRequestToken: LiveData<Response<CreateRequestTokenResponse>> = _getRequestToken


    fun createRequestToken() {
        viewModelScope.launch {
            _getRequestToken.value = authRepo.createAuthToken()
        }
    }

    fun createSession(
        username: String, password: String, requestToken: String
    ) {
        viewModelScope.launch {
            _getLoginSession.value = authRepo.createSession(password, requestToken, username)
        }
    }
}