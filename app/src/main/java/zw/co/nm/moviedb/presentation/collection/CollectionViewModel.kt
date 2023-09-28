package zw.co.nm.moviedb.presentation.collection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response

class CollectionViewModel(application: Application) : AndroidViewModel(application) {
    private val collectionRepo = CollectionRepo()

    private val _getCollectionDetail =
        MutableLiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetCollectionDetailResponse>>()
    val getCollectionDetail: LiveData<Response<zw.co.nm.moviedb.data.remote.model.responsemodel.GetCollectionDetailResponse>> =
        _getCollectionDetail

    fun getCollectionDetail(id: Int) {
        viewModelScope.launch {
            _getCollectionDetail.value = collectionRepo.getCollectionDetail(id)
        }
    }
}