package com.aoc.day2

import java.io.File

object CubeConundrum {

    private val RED = 12
    private val GREEN = 13
    private val BLUE = 14

    fun getPossibleGameIdSum(): Int {
        return readInput().stream().mapToInt {
            val gameId = it.substringBefore(":").substringAfter(" ").toInt()
            val gameString = it.substringAfter(":")
            val gamePossible = isGamePossible(gameString)
            if(gamePossible)
                gameId
            else
                0
        }.sum()
    }

    fun getSumOfPowerOfSetOfCubes() : Int {
        return readInput().stream().mapToInt {
            val gameString = it.substringAfter(":")
            getPowerOfSetOfCubes(gameString)
        }.sum()
    }

    private fun getPowerOfSetOfCubes(gameString: String) : Int {
        val gameList = gameString.split(";")
        var maxR = 1
        var maxG = 1
        var maxB = 1
        gameList.forEach { game ->
            val ballList = game.split(",")
            var r = 0
            var g = 0
            var b = 0
            ballList.forEach { ball ->
                r = if(r == 0) getRed(ball) else r
                g = if(g == 0) getGreen(ball) else g
                b = if(b == 0) getBlue(ball) else b
            }
            if(r > maxR) maxR = r
            if(g > maxG) maxG = g
            if(b > maxB) maxB = b
        }
        return maxR * maxG * maxB
    }

    private fun isGamePossible(gameString: String): Boolean {
        val gameList = gameString.split(";")
        gameList.forEach { game ->
            val ballList = game.split(",")
            var r = 0
            var g = 0
            var b = 0
            ballList.forEach { ball ->
                r = if(r == 0) getRed(ball) else r
                g = if(g == 0) getGreen(ball) else g
                b = if(b == 0) getBlue(ball) else b
            }
            if(r > RED || g > GREEN || b > BLUE) {
                return false
            }
        }
        return true
    }

    private fun getRed(str: String) : Int {
        if(str.contains("red")) {
            return str.substringBefore("red").trim().toInt()
        }
        return 0
    }

    private fun getGreen(str: String) : Int {
        if(str.contains("green")) {
            return str.substringBefore("green").trim().toInt()
        }
        return 0
    }

    private fun getBlue(str: String) : Int {
        if(str.contains("blue")) {
            return str.substringBefore("blue").trim().toInt()
        }
        return 0
    }

    private fun readInput() : List<String> {
//        val testData = """
//            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
//            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
//            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
//            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
//            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
//        """.trimIndent()
//        return testData.lines()
        return File("/Users/sunilnair/Git/AdventOfCode_2023/src/main/resources/day_2_input.txt").readLines()
    }
}