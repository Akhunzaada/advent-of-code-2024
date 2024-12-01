import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val listOne = mutableListOf<Int>()
        val listTwo = mutableListOf<Int>()
        input.forEach { line ->
            line.split(" ").let {
                listOne.add(it.first().toInt())
                listTwo.add(it.last().toInt())
            }
        }
        listOne.sort()
        listTwo.sort()

        var result = 0
        for (i in 0 until listOne.size) {
            result += abs(listOne[i].minus(listTwo[i]))
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val listOne = mutableListOf<Int>()
        val listTwo = mutableMapOf<Int, Int>()
        input.forEach { line ->
            line.split(" ").let {
                listOne.add(it.first().toInt())
                listTwo[it.last().toInt()] = (listTwo[it.last().toInt()] ?: 0).plus(1)
            }
        }

        return listOne.sumOf {
            it.times(listTwo[it] ?: 0)
        }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
