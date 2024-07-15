package com.vivianrosa.findtime.timezone

import com.vivianrosa.findtime.logging.Log
import com.vivianrosa.findtime.timezone.format.getFormattedDate
import com.vivianrosa.findtime.timezone.format.getFormattedTime
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

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
        val goodHours = mutableListOf<Int>()
        val timeRange = IntRange(max(0, startHour), min(23, endHour))
        val currentTimeZone = TimeZone.currentSystemDefault()

        for (hour in timeRange) {
            for (zone in timeZoneStrings) {
                val timezone = TimeZone.of(zone)
                if (timezone == currentTimeZone) {
                    continue
                }

                val isGoodHour = isValid(
                    timeRange = timeRange,
                    hour = hour,
                    currentTimeZone = currentTimeZone,
                    otherTimeZone = timezone
                )

                val message = if (isGoodHour) "valid" else "invalid"
                Log.d("Hour $hour is $message for time range")
                if (isGoodHour) {
                    goodHours.add(hour)
                }
            }
        }

        return goodHours
    }

    private fun getDateTime(timeZone: TimeZone) = Clock.System.now().toLocalDateTime(timeZone)

    private fun isValid(timeRange: IntRange, hour: Int, currentTimeZone: TimeZone, otherTimeZone: TimeZone): Boolean {
        if (hour !in timeRange) {
            return false
        }

        val currentOtherDateTime = getDateTime(otherTimeZone)
        val otherDateTimeWithHour = LocalDateTime(year = currentOtherDateTime.year,
            monthNumber = currentOtherDateTime.monthNumber,
            dayOfMonth = currentOtherDateTime.dayOfMonth,
            hour = hour,
            minute = 0,
            second = 0,
            nanosecond = 0
        )
        val localInstant = otherDateTimeWithHour.toInstant(currentTimeZone)
        val convertedTime = localInstant.toLocalDateTime(otherTimeZone)

        Log.d("Hour $hour in Time Range ${otherTimeZone.id} is ${convertedTime.hour}")
        return convertedTime.hour in timeRange
    }
}