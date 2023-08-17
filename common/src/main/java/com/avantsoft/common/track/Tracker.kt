package com.avantsoft.common.track

import android.util.Log

const val TAG = "WTracker"
private const val UNKNOWN = "UNKNOWN"
private const val PROJECT_PATH = "com.avantsoft"
private const val TRACKER_CLASS_PATH = "com.avantsoft.common.track.TrackerKt"
const val MESSAGE_DIVISOR = ":::"

object TrackerConfig {
    var useLog = true
}

inline fun track(description: String? = "", block: () -> Unit) {
    if (TrackerConfig.useLog) {
        Log.d(
            TAG,
            "${getFromAndMethod()} ${compileDescription(description)}"
        )
    } else {
        println("${getFromAndMethod()}${compileDescription(description)}")
    }
    block()
}

inline fun <T> track(description: String? = "", block: () -> T): T {
    if (TrackerConfig.useLog) {
        Log.d(TAG, "${getFromAndMethod()}${compileDescription(description)}")
    } else {
        println("${getFromAndMethod()}${compileDescription(description)}")
    }
    return block()
}

fun track(description: Any? = "") {
    if (TrackerConfig.useLog) {
        Log.d(TAG, "${getFromAndMethod()}${compileDescription(description.toString())}")
    } else {
        println("${getFromAndMethod()}${compileDescription(description.toString())}")
    }
}

fun track(error: Throwable) {
    if (TrackerConfig.useLog) {
        Log.d(TAG, "${getFromAndMethod()}${compileDescription("Failure=${error.message}")}")
    } else {
        println("${getFromAndMethod()}${compileDescription("Failure=${error.message}")}")
    }
}

fun compileDescription(description: String?): String =
    if (description.isNullOrEmpty()) "" else "$MESSAGE_DIVISOR $description"

fun getFromAndMethod(): String {
    for (i in 0 until Thread.currentThread().stackTrace.size) {
        val name = Thread.currentThread().stackTrace[i].className
        if (name.contains(PROJECT_PATH) && name != TRACKER_CLASS_PATH) {
            return name.split(".").let { it[it.lastIndex] } +
                    MESSAGE_DIVISOR +
                    Thread.currentThread().stackTrace[i].methodName
        }
    }

    return UNKNOWN
}