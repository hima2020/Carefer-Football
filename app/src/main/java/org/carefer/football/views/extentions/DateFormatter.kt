package org.carefer.football.views.extentions

import java.text.SimpleDateFormat
import java.util.*

fun String.getDate(
    dateFormat: String = "yyyy-MM-dd",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): String {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone

    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(parser.parse(this))
}

fun String.getTime(
    dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): String {

    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone

    val formatter = SimpleDateFormat("hh:mm", Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(parser.parse(this))
}