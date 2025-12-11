import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readText().lines()
    println(Day6(input.map { it.toList().map { it.toString() } }).part2())
}

class Day6(val input: List<List<String>>) {
    fun part2(): Long {
        val rotated = rotateCounterclockwise(input.filter { it.size != 0 })
        val result = emptyList<MutableList<MutableList<String>>>().toMutableList()
        val tempList = emptyList<MutableList<String>>().toMutableList()
        for (row in rotated) {
            if (!row.all { it == " " }) {
                tempList.add(row.toMutableList())
            } else {
                result.add(tempList.toMutableList())
                tempList.clear()
            }
        }
        result.add(tempList.toMutableList())
        val filtered = result.map { group ->
            val sign = group.last().last()
            sign to group.map { row ->
                row.filter { !it.isBlank()  && !it.equals(sign) }.joinToString("").toInt()
            }.toIntArray()
        }
        val add : (IntArray) -> Long = {
            val result = it.sum()
            println("ADD Array: ${it.size} result ${result}")
            result.toLong()
        }
        val multiply: (IntArray) -> Long = {
            it.fold(1L) { acc, i -> acc * i }
        }

        var count = 0L
        filtered.forEach {
            val sign = findSign(it.first)
            count += when (sign) {
                Sign.ADD -> add(it.second)
                Sign.MULTIPLY -> multiply(it.second)
            }
        }

        println("Final: " + count)

        return 0
    }

    fun rotateCounterclockwise(matrix: List<List<String>>): List<List<String>> {
        val cols = matrix[0].size
        return (cols - 1 downTo 0).map { col ->
            matrix.map { row -> row[col] }
        }
    }

    fun part1(): Long {
        val add : (IntArray) -> Long = {
            val result = it.sum()
            println("ADD Array: ${it.size} result ${result}")
            result.toLong()
        }
        val multiply: (IntArray) -> Long = {
            it.fold(1L) { acc, i -> acc * i }
        }
        var count = 0L


        var transposed = transpose(input)
        println(transposed)
        count = transposed.map {
            val sign = findSign(it.last())
            when (sign) {
                Sign.ADD -> add(it.dropLast(1).map { it.toInt() }.toIntArray())
                Sign.MULTIPLY -> multiply(it.dropLast(1).map { it.toInt() }.toIntArray())
            }
        }.sum()
        return count
    }

    fun transpose(input: List<List<String>>) : List<List<String>> {
        val cols = input[0].size
        return List<List<String>>(cols) { col ->
            List<String>(input.size) {
                row -> input[row][col]
            }
        }
    }

    fun findSign(s: String) : Sign {
        return when (s) {
            "+" -> Sign.ADD
            "*" -> Sign.MULTIPLY
            else -> throw Exception("Sign not found: $s")
        }
    }
}

enum class Sign {
    ADD(),
    MULTIPLY()
}

