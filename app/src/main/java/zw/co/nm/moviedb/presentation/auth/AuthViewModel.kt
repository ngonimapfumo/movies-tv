package zw.co.nm.moviedb.presentation.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.model.response.CreateSessionResponse
import zw.co.nm.moviedb.data.remote.util.Response

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepo = AuthRepo()

    private val _getLoginSession = MutableLiveData<Response<CreateSessionResponse>>()
    val getLoginSession: LiveData<Response<CreateSessionResponse>> = _getLoginSession

    suspend fun createSession(password: String, requestToken: String, username: String) {
        viewModelScope.launch {
            _getLoginSession.value = authRepo.createSession(password, requestToken, username)
        }
    }
}