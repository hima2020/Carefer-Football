package org.carefer.football.ui.home.data.datasource

import org.carefer.football.ui.home.data.model.MatchListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v4/matches/")
    suspend fun getMatchList(
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String,
    ): Response<MatchListResponse>

}