package org.carefer.football.ui.home.data.model

import org.carefer.football.utils.Utils
import org.threeten.bp.LocalDate
import java.util.*


open class ItemModel(
    var utcDate: String,
    var competition: String?
) {
    constructor(utcDate: String) : this(utcDate, null)

    open val dateTime: Date
        get() = Utils.toDate(utcDate)!!

    open val shortDate: Date
        get() = Utils.toDate(localDate)

    open val localDate: LocalDate
        get() = Utils.toLocalDate(utcDate)
}