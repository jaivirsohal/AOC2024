package Day2

import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("src/Day2/input.txt").readLines()

    println("Num of safe reports: ${calcSafe(input)}")
    println("Num of new safe reports: ${safeDampener(input)}")
}

fun calcSafe(input: List<String>): Int {
    val reports = input.map { it.split(" ").map { it.toInt() } }
    val diffs = reports.map { it.zipWithNext { a, b -> b - a } }

    val numSafe = diffs.filter { isSafe(it) }
    return numSafe.size
}

fun safeDampener(input: List<String>): Int {
    val reports = input.map { it.split(" ").map { it.toInt() } }

    val safeReports = reports.filter { report ->
        val diffs = report.zipWithNext { a, b -> b - a }
        if (isSafe(diffs)) {
            true
        } else {
            diffs.indices.any { idx ->
                val newDiffs = diffs.filterIndexed { index, _ -> index != idx }
                isSafe(newDiffs)
            }
        }
    }

    return safeReports.size
}

fun isSafe(diffs: List<Int>): Boolean {
    return (diffs.all { it > 0 } || diffs.all { it < 0 }) && diffs.all { abs(it) in 1..3 }
}
