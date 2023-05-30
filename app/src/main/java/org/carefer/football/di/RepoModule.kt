package org.carefer.football.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.carefer.football.base.BaseRemoteDataSource
import org.carefer.football.ui.home.data.datasource.ApiService
import org.carefer.football.ui.home.data.repo.MatchesRepoImpl
import org.carefer.football.ui.home.domain.repo.MatchesRepo

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepoModule(apiService: ApiService): BaseRemoteDataSource {
        return MatchesRepoImpl(apiService)

    }
}