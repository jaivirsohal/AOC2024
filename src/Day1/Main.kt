package Day1

import java.io.File
import kotlin.math.abs

fun solution(input: List<String>): Pair<Int, Int> {
    //gen pairs
    val pairs = input
        .map { it.split(Regex("\\s{3,}")) }

    //get lists
    val l1 = pairs.map { it[0].toInt() }.sorted()
    val l2 = pairs.map { it[1].toInt() }.sorted()

    //sum distances
    val distanceTot = l1.zip(l2) {a, b -> abs(a-b)}.sum()

    //calc score
    val score = l1.map { a -> a * (l2.count { a == it})}

    return distanceTot to score.sum()
}

fun main() {
    var input = File("src/Day1/input.txt").bufferedReader().readLines()
    val solutions = solution(input)

    println("Total distance: ${solutions.first}")
    println("Score: ${solutions.second}")

}
