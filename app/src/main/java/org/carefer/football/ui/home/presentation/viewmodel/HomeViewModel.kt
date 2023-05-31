package org.carefer.football.ui.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.carefer.football.base.Result
import org.carefer.football.core.MatchesDao
import org.carefer.football.ui.home.data.model.MatchListResponse
import org.carefer.football.ui.home.data.model.MatchModel
import org.carefer.football.ui.home.data.repo.MatchesRepoImpl
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val matchRepository: MatchesRepoImpl, private val dao: MatchesDao
) :
    ViewModel() {


    private val _res = MutableLiveData<MatchListResponse?>()
    val res: LiveData<MatchListResponse?> = _res


    private val _resultResponse = MutableLiveData<Result<MatchListResponse>>()
    val resultResponse: LiveData<Result<MatchListResponse>> = _resultResponse


    val favorites = MutableLiveData<List<MatchModel>>(null)
    suspend fun getMatchList(dateArgs: Pair<String, String>) {

        _res.postValue(null)
        _resultResponse.postValue(Result.loading(null))
        val res = matchRepository.getMatchList(dateArgs.first, dateArgs.second)

        _resultResponse.postValue(res)

    }

    fun getAllMatches() {
        viewModelScope.launch(Dispatchers.IO) {
            favorites.postValue(dao.getAllMatches())
        }
    }

    fun addToFav(match: MatchModel) {

        viewModelScope.launch(Dispatchers.IO) {
            val matches = dao.getAllMatches().map { it.id }
            if (matches.contains(match.id)) {
                dao.removeMatch(match)
            } else {
                dao.insertMatch(match)
            }

            favorites.postValue(dao.getAllMatches())


        }
    }


}