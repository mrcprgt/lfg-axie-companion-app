package com.lfgcompany.lfgaxiecompanionapp.tools.helpers

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun epochToDate(s: String): Date {
    return try {
//        val sdf = SimpleDateFormat("MM/dd/yyyy 'at' hh:mm aaa", Locale.getDefault())
        val netDate = Date(s.toLong() * 1000)

        Log.e("Epoch to date", netDate.toString())
        netDate
    } catch (e: Exception) {
        throw e
    }
}

fun daysDifference(d1: Date, d2: Date): Int {
    val diff = d2.time - d1.time

    val value = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
    return value.toInt()


}

fun formatMinutes(minutes: Int): Int {
    return if (minutes < 30) 0 else 30
}

fun DateTime.isToday(): Boolean = LocalDate.now().compareTo(LocalDate(this)) == 0

@SuppressLint("DefaultLocale")
fun LocalDateTime.getWeekName(): String = this.dayOfWeek().asText.lowercase(Locale.getDefault())

fun isToday(timestamp: Long): Boolean {
    val now = Calendar.getInstance()
    val timeToCheck = Calendar.getInstance()
    timeToCheck.timeInMillis = timestamp
    return (
            now[Calendar.YEAR] == timeToCheck[Calendar.YEAR] &&
                    now[Calendar.DAY_OF_YEAR] == timeToCheck[Calendar.DAY_OF_YEAR]
            )
}

fun Long.formatToDateWithPattern(pattern: String): String {
    return DateTimeFormat.forPattern(
        pattern
    ).print(this)
}

fun Long.formatToDateWithPatternHHMMA(): String {
    return formatToDateWithPattern("hh:mm a").toAMPM()
}

@SuppressLint("SimpleDateFormat")
fun Date.formatToHHMMA(): String {
    val dateFormat = SimpleDateFormat("hh:mm:a")

    return dateFormat.format(this)
}

fun Date.formatToMMDDYYYATHHMMAAAA(): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy 'at' hh:mm aaa", Locale.getDefault())
    return formatter.format(this)
}

fun Date.formatToUTC(): String {
    val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    utcFormat.timeZone = TimeZone.getTimeZone("UTC")

    return utcFormat.format(this)
}

fun Date.formatToMMDDYYYY(): String {
    val utcFormat = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
    utcFormat.timeZone = TimeZone.getTimeZone("UTC")

    return utcFormat.format(this)
}


fun Date.formatToMMMMdd(): String {
    val utcFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
    utcFormat.timeZone = TimeZone.getTimeZone("UTC")

    return utcFormat.format(this)
}

fun String.toLocalJavaDateFromUTC(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return format.parse(this)!!
}

@SuppressLint("SimpleDateFormat")
fun String.toJavaDateFromUTC(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    format.timeZone = TimeZone.getTimeZone("UTC")
    return format.parse(this)!!
}

@SuppressLint("SimpleDateFormat")
fun Date.toUTC(): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    format.timeZone = TimeZone.getTimeZone("UTC")
    return format.format(this)
}

fun Date.toUTCFromLocalDate(): String {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    format.timeZone = TimeZone.getTimeZone("UTC")
    return format.format(this)
}

fun String.parseUTCDate(): Date {
    val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    utcFormat.timeZone = TimeZone.getTimeZone("UTC")
    return utcFormat.parse(this)!!
}

fun Double.toCurrencyFormat(): String {
    val currencyFormat by lazy { DecimalFormat("###,###,###,##0.00") }

    return "₱ ${currencyFormat.format(this)}"
}

fun String.capitalizeEachWord(): String {
    return this.split(" ")
        .joinToString(" ") { s ->
            s.toLowerCase().capitalize()
        }
}

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.toAM(): String {
    return this.replace("am", "AM")
}

fun String.toPM(): String {
    return this.replace("pm", "PM")
}

fun String.toAMPM(): String {
    return this.toAM().toPM()
}

@SuppressLint("SimpleDateFormat")
fun dateIsToday(date: Date): Boolean {
    val df = SimpleDateFormat("yyyy-MM-dd")
    val today = df.format(Date())
    val given = df.format(date)

    return today.equals(given)
}