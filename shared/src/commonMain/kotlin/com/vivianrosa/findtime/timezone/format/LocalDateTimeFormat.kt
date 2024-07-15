package com.vivianrosa.findtime.timezone.format

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.byUnicodePattern

fun LocalDateTime.getFormattedDate(): String {
    return date.format(LocalDate.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        chars(", ")
        monthName(MonthNames.ENGLISH_FULL)
        chars(" ")
        dayOfMonth()
    })
}

@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDateTime.getFormattedTime(): String =
    time.format(LocalTime.Format { byUnicodePattern("HH:mm") })