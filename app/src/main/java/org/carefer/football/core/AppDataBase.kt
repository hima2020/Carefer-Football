package org.carefer.football.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.carefer.football.ui.home.data.model.MatchModel
import org.carefer.football.ui.home.data.model.ObjectConverter

@Database(entities = [MatchModel::class], version = 1)
@TypeConverters(ObjectConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchesDao(): MatchesDao


}