fun main() {
    fun List<String>.lookup(i: Int, j: Int, word: String): Int {
        var count = 0
        val vSize = this.size-3; val hSize = this[i].length-3
        val firstChar = this[i][j]
        if (j < hSize && this[i].substring(j).startsWith(word)) count++
        if (i < vSize) {
            if ("$firstChar${this[i+1][j]}${this[i+2][j]}${this[i+3][j]}" == word) count++
            if (j >= 3 && "$firstChar${this[i+1][j-1]}${this[i+2][j-2]}${this[i+3][j-3]}" == word) count++
            if (j < hSize && "$firstChar${this[i+1][j+1]}${this[i+2][j+2]}${this[i+3][j+3]}" == word) count++
        }
        return count
    }

    fun part1(input: List<String>): Int {
        var found = 0
        input.forEachIndexed { i, line ->
            line.forEachIndexed { j, ch ->
                found += input.lookup(i, j, if (ch == 'X') "XMAS" else "SAMX")
            }
        }
        return found
    }

    fun part2(input: List<String>): Int {
        var found = 0
        for (i in 0 until input.size-2) {
            val line = input[i]
            for (j in 0 until line.length-2) {
                val curr = line[j]; val next = line[j+2]
                if ((curr == 'M' || curr == 'S') && (next == 'M' || next == 'S')) {
                    if (((curr == 'M' && input[i+1][j+1] == 'A' && input[i+2][j+2] == 'S') ||
                                (curr == 'S' && input[i+1][j+1] == 'A' && input[i+2][j+2] == 'M')) &&
                        ((next == 'S' && input[i+1][j+1] == 'A' && input[i+2][j] == 'M') ||
                                (next == 'M' && input[i+1][j+1] == 'A' && input[i+2][j] == 'S'))) {
                        found++
                    }
                }
            }
        }
        return found
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
