package org.carefer.football.ui.home.data.model

import org.carefer.football.utils.Utils


class DateModel(
    utcDate: String
) : ItemModel(utcDate) {

    val dayOfWeek: String
        get() {
            return if (Utils.daysDifference(localDate) <= 1) Utils.relativeDay(shortDate.time)
            else Utils.dayOfWeekFormat(shortDate.time)
        }

    val dateOfMonth: String
        get() = Utils.shortRelativeDate(shortDate.time)

}