package com.vivianrosa.findtime.timezone

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs

class TimeZoneHelperImpl : TimeZoneHelper {
    override fun getTimeZoneStrings() = TimeZone.availableZoneIds.sorted()

    override fun currentTime() =
        getDateTime(TimeZone.currentSystemDefault()).getFormattedTime()

    override fun currentTimeZone() = TimeZone.currentSystemDefault().toString()

    override fun hoursFromTimeZone(otherZoneId: String): Double {
        val currentTimeZone = TimeZone.currentSystemDefault()
        val currentDateTime = getDateTime(currentTimeZone)

        val otherTimeZone = TimeZone.of(otherZoneId)
        val currentOtherDateTime = getDateTime(otherTimeZone)

        return abs(currentDateTime.hour - currentOtherDateTime.hour) * 1.0
    }

    override fun getTime(zoneId: String) = getDateTime(TimeZone.of(zoneId)).getFormattedTime()

    override fun getDate(zoneId: String) = getDateTime(TimeZone.of(zoneId)).getFormattedDate()

    override fun search(startHour: Int, endHour: Int, timeZoneStrings: List<String>): List<Int> {
        return listOf<Int>()
    }

    private fun getDateTime(timeZone: TimeZone) = Clock.System.now().toLocalDateTime(timeZone)
}