package zw.co.nm.moviedb.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import zw.co.nm.moviedb.model.GetPersonResponse
import zw.co.nm.moviedb.data.api.Response
import zw.co.nm.moviedb.repo.PersonRepo

class PersonViewModel(application: Application) :
    AndroidViewModel(application) {

    private val personRepo = PersonRepo()

    fun getPerson(personId: Int): Flow<Response<GetPersonResponse>> {
        return personRepo.getPerson(personId)
    }

}