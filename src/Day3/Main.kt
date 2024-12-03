package Day3

import java.io.File

fun main() {
    val input = File("src/Day3/input.txt").readText()
    val unfiltered = part1(input)
    val filtered = part2(input)
    println("Total sum of multiplications: $unfiltered")
    println("Total sum of enabled multiplications: $filtered")

}

fun part1 (input: String): Int {
    val mulReg = Regex("""mul\((\d+),(\d+)\)""")
    var sum = 0

    val matches = mulReg.findAll(input)
    matches.forEach { match ->
        val (x, y) = match.destructured
        sum += (x.toInt() * y.toInt())
    }
    return sum
}

fun part2 (input: String): Int {
    val mulReg = Regex("""mul\((\d+),(\d+)\)""")
    var sum = 0

    val matches = mulReg.findAll(input)
    matches.forEach { match ->
        if (isEnabled(input, match.range.first)) {
            val (x, y) = match.destructured
            sum += (x.toInt() * y.toInt())
        }
    }
    return sum
}

fun isEnabled(input: String, index: Int): Boolean {
    val lim = input.substring(0..index)
    val doIndex = lim.lastIndexOf("do()")
    val dontIndex = lim.lastIndexOf("don't()")
    return (doIndex >= dontIndex)
}