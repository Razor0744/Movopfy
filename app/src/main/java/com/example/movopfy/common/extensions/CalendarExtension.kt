package com.example.movopfy.common.extensions

import java.util.Calendar

const val SUNDAY_DAY_NUMBER = 1
const val MONDAY_DAY_NUMBER = 2
const val LAST_DAY_NUMBER = 6

fun Calendar.currentDay(): Int {
    val calendarDay = this.get(Calendar.DAY_OF_WEEK)

    return if (calendarDay == SUNDAY_DAY_NUMBER) LAST_DAY_NUMBER else calendarDay - MONDAY_DAY_NUMBER
}