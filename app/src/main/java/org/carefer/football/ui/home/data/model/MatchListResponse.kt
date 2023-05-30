package org.carefer.football.ui.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MatchListResponse(
    @SerialName("filters")
    val filters: Filters?,
    @SerialName("matches")
    val matches: List<Matche?>?,
    @SerialName("resultSet")
    val resultSet: ResultSet?
)

@Serializable
data class Filters(
    @SerialName("dateFrom")
    val dateFrom: String?,
    @SerialName("dateTo")
    val dateTo: String?,
    @SerialName("permission")
    val permission: String?
)

@Serializable
data class Matche(
    @SerialName("area")
    val area: Area?,
    @SerialName("awayTeam")
    val awayTeam: Team?,
    @SerialName("competition")
    val competition: Competition?,
    @SerialName("group")
    val group: Any?,
    @SerialName("homeTeam")
    val homeTeam: Team?,
    @SerialName("id")
    val id: Int?,
    @SerialName("lastUpdated")
    val lastUpdated: String?,
    @SerialName("matchday")
    val matchday: Int?,
    @SerialName("odds")
    val odds: Odds?,
    @SerialName("referees")
    val referees: List<Referee?>?,
    @SerialName("score")
    val score: Score?,
    @SerialName("season")
    val season: Season?,
    @SerialName("stage")
    val stage: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("utcDate")
    val utcDate: String?
)

@Serializable
data class ResultSet(
    @SerialName("competitions")
    val competitions: String?,
    @SerialName("count")
    val count: Int?,
    @SerialName("first")
    val first: String?,
    @SerialName("last")
    val last: String?,
    @SerialName("played")
    val played: Int?
)

@Serializable
data class Area(
    @SerialName("code")
    val code: String?,
    @SerialName("flag")
    val flag: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?
)

@Serializable
data class Team(
    @SerialName("crest")
    val crest: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("shortName")
    val shortName: String?,
    @SerialName("tla")
    val tla: String?
)

@Serializable
data class Competition(
    @SerialName("code")
    val code: String?,
    @SerialName("emblem")
    val emblem: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("type")
    val type: String?
)


@Serializable
data class Odds(
    @SerialName("msg")
    val msg: String?
)

@Serializable
data class Referee(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("nationality")
    val nationality: String?,
    @SerialName("type")
    val type: String?
)

@Serializable
data class Score(
    @SerialName("duration")
    val duration: String?,
    @SerialName("fullTime")
    val fullTime: FullTime?,
    @SerialName("halfTime")
    val halfTime: HalfTime?,
    @SerialName("winner")
    val winner: String?
)

@Serializable
data class Season(
    @SerialName("currentMatchday")
    val currentMatchday: Int?,
    @SerialName("endDate")
    val endDate: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("startDate")
    val startDate: String?,
    @SerialName("winner")
    val winner: Any?
)

@Serializable
data class FullTime(
    @SerialName("away")
    val away: Int?,
    @SerialName("home")
    val home: Int?
)

@Serializable
data class HalfTime(
    @SerialName("away")
    val away: Int?,
    @SerialName("home")
    val home: Int?
)
