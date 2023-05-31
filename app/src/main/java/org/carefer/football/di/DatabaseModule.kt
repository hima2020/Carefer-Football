package org.carefer.football.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.carefer.football.core.AppDatabase
import org.carefer.football.core.MatchesDao

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "my-db"
        ).build()
    }

    @Provides
    fun provideMatchesDao(database: AppDatabase): MatchesDao {
        return database.matchesDao()
    }
}