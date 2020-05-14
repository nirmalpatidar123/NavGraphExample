package com.example.mygallery.utils

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Utility {
    companion object {

        val DATE_FORMAT_STANDARD = "dd-MM-yyyy HH:mm"

        fun milliSecondToTime(millis: Long): String {
            return String.format(
                "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
            )
        }

        fun timestampToDate(timeStamp: Long, dateFormat: String): String {
            val cal = Calendar.getInstance(Locale.ENGLISH)
            cal.timeInMillis = timeStamp * 1000L
            val dateFormatter = SimpleDateFormat(dateFormat)
            return dateFormatter.format(cal.time)
        }
    }
}