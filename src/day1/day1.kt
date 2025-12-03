import java.io.File

data class Step(val mod: (Int, Int) -> Int, val clicks: Int)
data class Step2(val mod: (Int, Int) -> Pair<Int, Int>, val clicks: Int, val positive: Boolean)

fun main(args: Array<String>) {
    val input = File(args[0]).readLines()
    part2(input)
    part1(input)
}

fun part2(input: List<String>) : Int {
    val left: (Int, Int) -> Pair<Int, Int> = { c, t ->
        val newTicker = (((t - c) % 100) + 100) % 100
        newTicker to (c - t+ 99) / 100
    }
    val right : (Int, Int) -> Pair<Int, Int> = { c, t ->
        val newTicker = (((c + t) % 100) + 100) % 100
        newTicker to (newTicker + c) / 100
    }
   return input
        .map {
            val mod = if (it.substring(0, 1) == "L") left else right
            Step2(mod,it.substring(1).toInt(), it.substring(0, 1) == "R")
        }
        .fold(50 to 0) { (ticker, rotations), step ->
            val (newTicker, newRotation) = step.mod(step.clicks, ticker)
            (newTicker to rotations + newRotation)
        }.second
}

fun part1(input: List<String>) : Int {
    val left: (Int, Int) -> Int = { c, t ->
        (((c + t) % 100) + 100) % 100
    }
    val right : (Int, Int) -> Int = { c, t ->
        (((t- c) % 100) + 100) % 100
    }
    return input
        .map {
            val mod = if (it.substring(0, 1) == "L") left else right
            Step(mod,it.substring(1).toInt())
        }
        .fold(50 to 0) { (ticker, zeros), step ->
            val newTicker = step.mod(step.clicks, ticker)
            val newZero = zeros + if (newTicker == 0) 1 else 0
            (newTicker to newZero)
        }.second
}

