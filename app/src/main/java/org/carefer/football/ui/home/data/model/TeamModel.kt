package org.carefer.football.ui.home.data.model

import androidx.room.Entity


@Entity(tableName = "teams")
data class TeamModel(
    val id: Int,
    val name: String,
    val teamFlag: String
)