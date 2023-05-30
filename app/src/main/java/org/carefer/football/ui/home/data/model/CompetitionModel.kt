package org.carefer.football.ui.home.data.model

class CompetitionModel(
    utcDate: String,
) : ItemModel(utcDate) {

    val competitionName: String
        get() = utcDate
}

