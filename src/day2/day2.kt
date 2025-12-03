import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readText().trim().split(",")
    println(input)
    println(Day2(input).part2())
}

class Day2(val input: List<String>) {
    fun part2() : Long {
        val invalidIds = mutableListOf<Long>()
        input.forEach {
            val range = it.split("-")
            val start = range[0].trim().toLong()
            val end = range[1].trim().toLong()
            (start..end).forEach {
                if(match2(it.toString())) {
                    invalidIds.add(it)
                }
            }
        }
        return invalidIds.sum()
    }

    fun part1() : Long {
        val invalidIds = mutableListOf<Long>()
        input.forEach {
            val range = it.split("-")
            val start = range[0].trim().toLong()
            val end = range[1].trim().toLong()
            (start..end).forEach {
                if(match(it.toString())) {
                    invalidIds.add(it)
                }
            }
        }
        return invalidIds.sum()
    }

    fun match2(id: String): Boolean {
        return Regex("^([0-9]+)\\1+$").matches(id)
    }

    fun match(id: String): Boolean {
        println("id: ${id}")
        val middle = (id.toCharArray().size / 2)
        val first = id.take(middle)
        val second = id.drop(middle)
        if (first == second) {
            println("Invalid: " + id)
            return true
        }
        return false
    }

}
