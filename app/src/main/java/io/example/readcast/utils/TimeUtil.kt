package io.example.readcast.utils

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

fun String.getTimeAgo(): String {
    val parsedDate = Instant.parse(this)
    val now = ZonedDateTime.now(ZoneId.systemDefault())
    val time = parsedDate.atZone(ZoneId.systemDefault())

    val minutes = ChronoUnit.MINUTES.between(time, now)
    val hours = ChronoUnit.HOURS.between(time, now)
    val days = ChronoUnit.DAYS.between(time, now)
    val months = ChronoUnit.MONTHS.between(time, now)
    val years = ChronoUnit.YEARS.between(time, now)

    return when {
        years > 0 -> "$years year${if (years > 1) "s" else ""} ago"
        months > 0 -> "$months month${if (months > 1) "s" else ""} ago"
        days > 0 -> "$days day${if (days > 1) "s" else ""} ago"
        hours > 0 -> "$hours hour${if (hours > 1) "s" else ""} ago"
        minutes > 0 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
        else -> "Just now"
    }
}

fun Int.formatDurationToHM(): String {
    val seconds = this
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60

    return buildString {
        if (hours > 0) append("$hours h ")
        append("$minutes m")
    }.trim()
}

