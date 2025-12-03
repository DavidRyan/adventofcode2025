import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readText().trim().split("\n")
    println(input)
    println(Day3(input).part2())
}

class Day3(val input: List<String>) {
    fun part1(): Long {
        return input.sumOf { s ->
            val digits = s.map { it.digitToInt() }
            val result = mutableListOf<Int>()
            val remaining = 2

            for (i in digits.indices) {
                val available = digits.size - i
                while (result.isNotEmpty() &&
                    result.last() < digits[i] &&
                    result.size + available > remaining) {
                    result.removeLast()
                }
                if (result.size < remaining) {
                    result.add(digits[i])
                }
            }

            result.joinToString("").toLong()
        }
    }
    fun part2(): Long {
        return input.sumOf { s ->
            val digits = s.map { it.digitToInt() }
            val result = mutableListOf<Int>()
            val remaining = 12

            for (i in digits.indices) {
                val available = digits.size - i
                while (result.isNotEmpty() &&
                    result.last() < digits[i] &&
                    result.size + available > remaining) {
                    result.removeLast()
                }
                if (result.size < remaining) {
                    result.add(digits[i])
                }
            }

            result.joinToString("").toLong()
        }
    }
}
