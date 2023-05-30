package org.carefer.football.ui.home.data.model

import org.carefer.football.utils.Utils


class MatchModel(
    val homeTeam: TeamModel,
    val homeTeamScore: Int?,
    val awayTeam: TeamModel,
    val awayTeamScore: Int?,
    utcDate: String,
    val status: String,
    val matchday: String,
    val homeFlag:String?,
    val awayFlag:String?
) : ItemModel(utcDate) {

    val shortTime: String
        get() = Utils.clockFormat(dateTime)
}