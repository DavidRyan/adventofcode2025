import java.io.File
import java.lang.StrictMath.pow
import java.util.PriorityQueue
import java.util.Stack
import java.util.TreeSet
import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val input = File(args[0]).readText().trim().lines()
    val formattted = input.map { it.split(",").map { it.toDouble() } }

    Day8(formattted.map{
        Day8.Point(it[0], it[1], it[2])
    }).part2()
}

class Day8(val input: List<Point>) {

    data class Point(val x: Double, val y: Double, val z: Double)

    fun distance(p1: Point, p2: Point): Double {
        return sqrt(
            (p2.x - p1.x).pow(2) +
                    (p2.y - p1.y).pow(2) +
                    (p2.z - p1.z).pow(2)
        )
    }

    fun part1() {
        val treeSet = TreeSet<Pair<Set<Point>, Double>>(compareBy {
            it.second
        })
        val distanceHeap = PriorityQueue<Pair<Pair<Point, Point>, Double>>(compareByDescending {
            it.second
        })

        for (i in input.indices) {
            for (j in input.indices) {
                val distance = distance(input[i], input[j])
                if (distance != 0.0) {
                    //println("Offering: ${(input[i] to input[j])}")
                    distanceHeap.offer(((input[i] to input[j]) to distance))
                    treeSet.add(setOf(input[i], input[j]) to distance)
                }
            }
        }

        treeSet.forEach {
            //println(it)
        }
        val circuitSet : MutableList<MutableSet<Point>> = input.map { mutableSetOf(it) }.toMutableList()

        for (i in 0..9) {
            val nextClosest = treeSet.pollFirst()

            val nextClosestA = nextClosest.first.first()
            val nextClosestB = nextClosest.first.last()

            println("Next Closest: | $nextClosestA | $nextClosestB")

            val potentialSetA = circuitSet.indexOfFirst{
                it.contains(nextClosestA)
            }
            val potentialSetB = circuitSet.indexOfFirst{
                it.contains(nextClosestB)
            }

            if (potentialSetB != potentialSetA) {
                println("a: $potentialSetA b: $potentialSetB")

                // merge the two sets
                val new = circuitSet[potentialSetA] + circuitSet[potentialSetB]

                val setA = circuitSet[potentialSetA]
                val setB = circuitSet[potentialSetB]
                circuitSet.removeAll(listOf(setA, setB))
                circuitSet.add(new.toMutableSet())


                // TODO: merge the a and b set


                circuitSet.forEach {
                    println(it)
                }
            }

        }
        val result = circuitSet.sortedByDescending { it.size }.map { it.size }.take(3)
            .reduce { acc, i ->  acc * i}
        println("-0---------- ${result}")
    }

    fun part2() {
        val treeSet = TreeSet<Pair<Set<Point>, Double>>(compareBy {
            it.second
        })
        val distanceHeap = PriorityQueue<Pair<Pair<Point, Point>, Double>>(compareByDescending {
            it.second
        })

        for (i in input.indices) {
            for (j in input.indices) {
                val distance = distance(input[i], input[j])
                if (distance != 0.0) {
                    //println("Offering: ${(input[i] to input[j])}")
                    distanceHeap.offer(((input[i] to input[j]) to distance))
                    treeSet.add(setOf(input[i], input[j]) to distance)
                }
            }
        }

        treeSet.forEach {
            //println(it)
        }
        val circuitSet : MutableList<MutableSet<Point>> = input.map { mutableSetOf(it) }.toMutableList()

        var last: Set<Point>? = null
        while (circuitSet.size > 1 && treeSet.isNotEmpty()) {
            val nextClosest = treeSet.pollFirst()
            last = nextClosest.first

            val nextClosestA = nextClosest.first.first()
            val nextClosestB = nextClosest.first.last()

            println("Next Closest: | $nextClosestA | $nextClosestB")

            val potentialSetA = circuitSet.indexOfFirst{
                it.contains(nextClosestA)
            }
            val potentialSetB = circuitSet.indexOfFirst{
                it.contains(nextClosestB)
            }

            if (potentialSetB != potentialSetA) {
                println("a: $potentialSetA b: $potentialSetB")

                // merge the two sets
                val new = circuitSet[potentialSetA] + circuitSet[potentialSetB]

                val setA = circuitSet[potentialSetA]
                val setB = circuitSet[potentialSetB]
                circuitSet.removeAll(listOf(setA, setB))
                circuitSet.add(new.toMutableSet())


                // TODO: merge the a and b set


                circuitSet.forEach {
                    println(it)
                }
            }

        }
        val result = (last!!.first().x * last.last().x)
        println("-0---------- ${result}")
    }
}
