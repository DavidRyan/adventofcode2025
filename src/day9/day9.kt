
import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow

fun main(args: Array<String>) {
    val input = File(args[0]).readText().trim().split("\n")
    println(input)
    println(Day9(input).part1())
}

class Day9(val input: List<String>) {

    fun distance(x1: Int, y1: Int, x2: Int, y2: Int): Double{
        return abs(x1 - x2).toDouble().pow(2) + abs(y1 - y2).toDouble().pow(2)
    }

    fun area(p1: Point, p2: Point): Long {
        return (abs(p2.x.toLong() - p1.x.toLong())+1) * (abs(p2.y.toLong() - p1.y.toLong())+1)
    }

    data class Point(val x: Int, val y: Int)

    fun part2() : Long {
        // find all red/green tiles
        // find max area between them but validate they are all red/green inside
        // (brute force)
    }

    fun part1(): Long {
        val points = input.map {
            val xy = it.split(",")
            println("point $xy")
            Point(xy.first().toInt(), xy.last().toInt())
        }

        println("points $points")

        var maxArea = 0L
        points.forEach { p ->
            points.filter { p != it }.forEach { p2 ->
                if (p != p2) {
                    val area = area(p, p2)
                    if (area > maxArea) {
                        println("P1 $p P2 $p2 $area")
                        maxArea = area(p, p2)
                    }
                }
            }
        }

        return maxArea
    }
}
