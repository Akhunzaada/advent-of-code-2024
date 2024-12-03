import kotlin.math.abs

fun main() {
    fun evalMulExpr(op: String): Int {
        val split = op.split(",")
        return split.first().split("(").last().toInt()
            .times(split.last().split(")").first().toInt())
    }

    fun part1(input: List<String>): Int {
        val regex = "mul\\s*\\(\\s*\\d+(\\s*,\\s*\\d+)*\\s*\\)".toRegex()
        return input.sumOf { line ->
            regex.findAll(line).sumOf { op ->
                evalMulExpr(op.value)
            }
        }
    }

    fun part2(input: List<String>): Int {
        val regex = """(mul\s*\(\s*\d+(\s*,\s*\d+)*\s*\)|don't|do)""".toRegex()
        var enabled = true
        return input.sumOf { line ->
            regex.findAll(line).sumOf { op ->
                if (op.value == "don't" || op.value == "do") {
                    enabled = op.value != "don't"
                    0
                } else {
                    evalMulExpr(op.value).takeIf { enabled } ?: 0
                }
            }
        }
    }

    val testInput = readInput("Day03_test")
    val testInput2 = readInput("Day03_Part2_test")
    check(part1(testInput) == 161)
    check(part2(testInput2) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
