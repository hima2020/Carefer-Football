package org.carefer.football.ui.home.data.datasource

import io.reactivex.Single
import org.carefer.football.ui.home.data.model.MatchListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v4/matches/")
    suspend fun getMatchList(): Response<MatchListResponse>

}