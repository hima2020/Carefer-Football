package org.carefer.football.ui.home.data.repo

import org.carefer.football.base.BaseRemoteDataSource
import org.carefer.football.base.Result
import org.carefer.football.ui.home.data.datasource.ApiService
import org.carefer.football.ui.home.data.model.MatchListResponse
import javax.inject.Inject

class MatchesRepoImpl @Inject constructor(private val service: ApiService) :
    BaseRemoteDataSource() {

    suspend fun getMatchList(from: String, to: String): Result<MatchListResponse> {
        return getResult { service.getMatchList(from, to) }
    }
}