package com.aoc.day3

import com.sun.org.apache.xpath.internal.operations.Bool
import java.io.File

object GearRatios {

    fun getEnginePartNumberSum() : Long {
        val inputLines = readInput()
        val validEnginePartNumbers = mutableListOf<Long>()
        for(i in inputLines.indices) {
            print("${inputLines[i]} -> ")
            val currentLineArr = inputLines[i].toCharArray()
            var numStr = ""
            var foundDigit = false
            var isPartNumber = false
            for (j in currentLineArr.indices) {
                val c = currentLineArr[j]
                if(c.isDigit()) {
                    numStr += c
                    foundDigit = true
                    if(!isPartNumber) {
                        isPartNumber = when (i) {
                            0 -> {
                                //look for symbol in next line
                                val nextLineCharArr = inputLines[i + 1].toCharArray()
                                isSymbolPresent(nextLineCharArr, j, currentLineArr.indices.last)
                            }
                            inputLines.indices.last -> {
                                //look for symbol in previous line
                                val previousLineCharArr = inputLines[i - 1].toCharArray()
                                isSymbolPresent(previousLineCharArr, j, currentLineArr.indices.last)
                            }
                            else -> {
                                //look for symbols in previous and next line
                                val nextLineCharArr = inputLines[i + 1].toCharArray()
                                val previousLineCharArr = inputLines[i - 1].toCharArray()
                                isSymbolPresent(nextLineCharArr, j, currentLineArr.indices.last) || isSymbolPresent(previousLineCharArr, j, currentLineArr.indices.last)
                            }
                        }
                        //check current line
                        if(!isPartNumber) {
                            isPartNumber = when (j) {
                                0 -> {
                                    val nc = currentLineArr[j + 1]
                                    isSymbol(nc)
                                }
                                currentLineArr.indices.last -> {
                                    val pc = currentLineArr[j - 1]
                                    isSymbol(pc)
                                }
                                else -> {
                                    val nc = currentLineArr[j + 1]
                                    val pc = currentLineArr[j - 1]
                                    isSymbol(pc) || isSymbol(nc)
                                }
                            }
                        }
                    }
                } else {
                    if (foundDigit && isPartNumber) {
                        print("$numStr ")
                        validEnginePartNumbers.add(numStr.toLong())
                    }
                    numStr = ""
                    foundDigit = false
                    isPartNumber = false
                }
            }
            if (foundDigit && isPartNumber) {
                print("$numStr ")
                validEnginePartNumbers.add(numStr.toLong())
            }
            println()
        }
        return validEnginePartNumbers.stream().mapToLong{it}.sum()
    }

    private fun isSymbolPresent(charArray: CharArray, currentIndex: Int, lastIndex: Int) : Boolean {
        if(isSymbol(charArray[currentIndex])) {
            return true
        } else if(currentIndex != lastIndex && isSymbol(charArray[currentIndex + 1])) {
            return true
        } else if(currentIndex != 0 && isSymbol(charArray[currentIndex - 1])) {
            return true
        }
        return false
    }

    private fun isSymbol(c: Char) : Boolean {
        return (!c.isDigit() && c != '.')
    }

    private fun readInput() : List<String> {
//        val testData = """
//            467..114..
//            ...*......
//            ..35..633.
//            ......#...
//            617*......
//            .....+.58.
//            ..592.....
//            ......755.
//            ...${'$'}.*....
//            .664*598..
//        """.trimIndent()
//        return testData.lines()
        return File("/Users/sunilnair/Git/AdventOfCode_2023/src/main/resources/day_3_input.txt").readLines()
    }
}