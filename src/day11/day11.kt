import java.io.File
import java.lang.StrictMath.pow
import java.util.PriorityQueue
import java.util.Stack
import java.util.TreeSet
import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val input = File(args[0]).readText().trim().lines()
    val formatted = input.associate {
        val keyval = it.split(":")
        val name = keyval.first()
        val vertices = keyval.last().toString().trim().split(" ")
        name to vertices
    }
    Day11(formatted).part1()
}

class Day11(val input: Map<String, List<String>>) {
    fun part1() {

        fun traverse(name: String, vertices: List<String>) : Int {
            println("Name: $name")
            return vertices.sumOf { vert ->
                if (vert == "out") {
                    1
                } else {
                    println("Searching vert: $vert")
                    traverse(vert, input[vert]!!)
                }
            }
        }

        val count = traverse("you", input["you"]!!)

        println(count)
    }
}
