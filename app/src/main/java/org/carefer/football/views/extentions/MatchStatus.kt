package org.carefer.football.views.extentions

import org.carefer.football.R

fun String.getTextColor(): Int {
    return when (this) {
        "FINISHED" -> R.color.red_carefer
        "TIMED" -> R.color.grey_carefer
        else -> R.color.red_carefer
    }
}