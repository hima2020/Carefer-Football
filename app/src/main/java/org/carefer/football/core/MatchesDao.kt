package org.carefer.football.core

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.carefer.football.ui.home.data.model.MatchModel

@Dao
interface MatchesDao {

    @Query("SELECT * FROM Matches")
    fun getAllMatches(): List<MatchModel>

    @Insert()
    suspend fun insertMatch(model: MatchModel)

    @Delete()
    suspend fun removeMatch(model: MatchModel)


}