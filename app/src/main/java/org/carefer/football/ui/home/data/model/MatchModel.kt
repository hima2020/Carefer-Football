package org.carefer.football.ui.home.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.carefer.football.utils.Utils


@Entity(tableName = "Matches")
class MatchModel(
    @PrimaryKey
    val id: Int,
    val homeTeamName: String,
    val homeTeamFlag: String?,
    val homeTeamScore: Int?,
    val awayTeamName: String,
    val awayTeamFlag: String?,
    val awayTeamScore: Int?,
    utcDate: String,
    val status: String,
    val matchday: String,
) : ItemModel(utcDate) {

    val shortTime: String
        get() = Utils.clockFormat(dateTime)
}