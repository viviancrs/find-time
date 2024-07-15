package com.vivianrosa.findtime.timezone

interface TimeZoneHelper {
    fun getTimeZoneStrings(): List<String>
    fun currentTime(): String
    fun currentTimeZone(): String
    fun hoursFromTimeZone(otherZoneId: String): Double
    fun getTime(zoneId: String): String
    fun getDate(zoneId: String): String
    fun search(startHour: Int, endHour: Int, timeZoneStrings: List<String>): List<Int>
}