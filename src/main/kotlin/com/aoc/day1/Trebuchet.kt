package com.aoc.day1

import java.io.File

object Trubuchet {

    fun calibrationValue() : Int {
        return readInput().stream().mapToInt {
            val num = getNumber(it)
            num
        }.sum()
    }

    private fun getNumber(str: String): Int {
        val spelledDigitMap = mapOf(
            "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5,
            "6" to 6, "7" to 7, "8" to 8, "9" to 9,
            "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
            "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

        val tensDigitMap = mutableMapOf<Int, String>()
        val onesDigitMap = mutableMapOf<Int, String>()
        spelledDigitMap.keys.forEach { k ->
            if(str.contains(k)) {
                val tensIndex = str.indexOf(k)
                tensDigitMap[tensIndex] = k
                val onesIndex = str.lastIndexOf(k)
                onesDigitMap[onesIndex] = k
            }
        }

        val tensDigit = spelledDigitMap[tensDigitMap[tensDigitMap.keys.min()]]!!
        val onesDigit = spelledDigitMap[onesDigitMap[onesDigitMap.keys.max()]]!!

        return (10 * tensDigit) + onesDigit
    }

    private fun readInput() : List<String> {
        return File("/Users/sunilnair/Git/AdventOfCode_2023/src/main/resources/day_1_input.txt").readLines()
    }
}