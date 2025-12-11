import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readText().trim().lines()
    val split = input.indexOf("")
    val sets = input.subList(0, split)
    val inredients = input.subList(split+1, input.size)
    println(Day5(sets, inredients).part2())
}

class Day5(val input: List<String>, val ingredients: List<String>) {
    fun part2(): Long {
        val sets = input.map {
            val start = it.split("-").first().toLong()
            val end = it.split("-").last().toLong()
            start to end
        }.sortedBy { it.first }

        val merged = mutableListOf<Pair<Long, Long>>(sets[0])
        for(set in sets.drop(1)) {
            if (set.first <= merged.last().second + 1) {
                val newPair = merged.last().first to maxOf(set.second, merged.last().second)
                merged[merged.lastIndex] = newPair
            } else {
                merged.add(set)
            }
        }
        val sum = merged.map {
            it.second - it.first +  1
        }.sum()
        return sum
    }


    fun part1(): Int {

        val sets = input.map {
            val start = it.split("-").first().toLong()
            val end = it.split("-").last().toLong()
            start to end
        }
        println("Sets size: ${sets.size}")
        val filtered = ingredients.map { it.toLong() }.filter {
            for (set in sets) {
                if (it >= set.first && it <= set.second) {
                    return@filter true
                }
            }
            false
        }
        return filtered.size
    }
}

