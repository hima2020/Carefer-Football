package org.carefer.football.ui.home.domain.repo

import io.reactivex.Single
import org.carefer.football.ui.home.data.model.MatchListResponse
import retrofit2.Response

interface MatchesRepo {
    fun getMatchList(): Response<MatchListResponse>
}