package org.carefer.football.ui.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.carefer.football.base.Result
import org.carefer.football.ui.home.data.model.MatchListResponse
import org.carefer.football.ui.home.data.repo.MatchesRepoImpl
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val matchRepository: MatchesRepoImpl) :
    ViewModel() {


    private val _res = MutableLiveData<MatchListResponse?>()
    val res: LiveData<MatchListResponse?> = _res


    private val _resultResponse = MutableLiveData<Result<MatchListResponse>>()
    val resultResponse: LiveData<Result<MatchListResponse>> = _resultResponse
    suspend fun getMatchList(dateArgs: Pair<String, String>) {

        _res.postValue(null)
        _resultResponse.postValue(Result.loading(null))
        val res = matchRepository.getMatchList(dateArgs.first, dateArgs.second)

        _resultResponse.postValue(res)

    }


}