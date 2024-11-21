package com.example.timecalculator

class Operation(private val first: String, private val second: String) {

    fun sum() = transformToTime(transformToSec(first) + transformToSec(second))
    fun dif() = transformToTime(transformToSec(first) - transformToSec(second))

    fun transformToSec(time: String): Int {
        var timeH = time.substringBefore('h')
        if (!timeH.all { char -> char.isDigit() }) timeH = "0"
        var timeM = time.substringAfter('h').substringBefore('m')
        if (!timeM.all { char -> char.isDigit() }) timeM = "0"
        var timeS = time.substringAfter('m').substringBefore('s')
        if (!timeS.all { char -> char.isDigit() }) timeS = "0"
        val sec = (timeH.toInt() * 3600) + (timeM.toInt() * 60) + timeS.toInt()
        return sec
    }

    fun transformToTime(sec: Int): String {
        val result: String
        when(sec) {
            in 0..59 -> {
                result = sec.toString().plus('s')
            }
            in 60..<3600 -> {
                val min = sec / 60
                val secTime = sec % 60
                result = min.toString() + 'm' + secTime.toString() + 's'
            }
            else -> {
                val hour = sec / 3600
                val min = (sec % 3600) / 60
                val secTime = sec % 60
                result = hour.toString() + 'h' + min.toString() + 'm' + secTime.toString() + 's'
            }
        }
        return result
    }
}