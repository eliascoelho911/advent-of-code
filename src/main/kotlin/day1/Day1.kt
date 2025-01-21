package day1

import kotlin.math.abs
import util.InputReader

fun main(args: Array<String>) {
    val (firstColumn, secondColumn) = InputReader.readTwoLongColumns("day1/day1_input.txt").let {
        it.first.sorted() to it.second.sorted()
    }

    var sumOfDistance: Long = 0

    firstColumn.forEachIndexed { index, first ->
        sumOfDistance += abs(first - secondColumn[index])
    }

    println(sumOfDistance)

    val similarityScore = mutableMapOf<Long, Int>()
    var totalSimilarityScore = 0L
    var lastSecondColumnIndex = 0
    var match = false

    for (first in firstColumn) {
        if (similarityScore[first] != null && !match) {
            totalSimilarityScore += (similarityScore[first] ?: 0) * first
            continue
        }

        for (secondIndex in lastSecondColumnIndex..secondColumn.lastIndex) {
            val second = secondColumn[secondIndex]

            if (first == second) {
                similarityScore[first] = (similarityScore[first] ?: 0) + 1
                totalSimilarityScore += first
                match = true
            } else if (match) {
                match = false
                lastSecondColumnIndex = secondIndex
                break
            }
        }
    }

    print(totalSimilarityScore)
}