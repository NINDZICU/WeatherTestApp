package com.mera.weathertestapp.utils

import java.text.SimpleDateFormat
import java.util.*

private const val HOUR_24_PATTERN = "HH"
private const val HOUR_12_PATTERN = "h a"
private const val DATE_TIME_PATTERN = "EEE d/MM/yy h:mm a"

fun Int.to12HFormat(): String {
    val date = SimpleDateFormat(HOUR_24_PATTERN, Locale.US).parse(this.toString())
    date?.let {
        return SimpleDateFormat(HOUR_12_PATTERN, Locale.US).format(date)
    } ?: return ""
}

fun Date.toDateTimeString(): String =
    SimpleDateFormat(DATE_TIME_PATTERN, Locale.US).format(this)