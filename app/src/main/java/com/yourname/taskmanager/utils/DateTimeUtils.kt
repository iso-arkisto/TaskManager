package com.yourname.taskmanager.utils

import android.text.format.DateFormat
import java.util.Date

fun Long.toDateTimeString(pattern: CharSequence = "dd.MM.yyyy HH:mm"): String {
    return DateFormat.format(pattern, Date(this)).toString()
}