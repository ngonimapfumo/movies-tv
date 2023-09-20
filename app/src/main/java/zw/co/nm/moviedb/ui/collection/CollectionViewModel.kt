package zw.co.nm.moviedb.ui.collection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.data.remote.Response
import zw.co.nm.moviedb.data.remote.networkmodel.GetCollectionDetailResponse

class CollectionViewModel(application: Application) : AndroidViewModel(application) {
    private val collectionRepo = CollectionRepo()

    private val _getCollectionDetail = MutableLiveData<Response<GetCollectionDetailResponse>>()
    val getCollectionDetail: LiveData<Response<GetCollectionDetailResponse>> = _getCollectionDetail

    fun getCollectionDetail(id: Int) {
        viewModelScope.launch {
                _getCollectionDetail.value = collectionRepo.getCollectionDetail(id)
        }
    }
}