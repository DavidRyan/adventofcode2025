import java.io.File

enum class Direction {
    LEFT,
    RIGHT
}

data class Step(val mod: (Int, Int) -> Int, val clicks: Int)


fun main(args: Array<String>) {
    var ticker: Int = 50
    var zeros = 0
    val left: (Int, Int) -> Int = { c, t ->
        (((c + t) % 100) + 100) % 100
    }
    val right : (Int, Int) -> Int = { c, t ->
        (((t- c) % 100) + 100) % 100
    }
    File(args[0]).readLines()
        .map {
            val mod = if (it.substring(0, 1) == "L") left else right
            Step(mod,it.substring(1).toInt())
        }
        .forEach {
            ticker = it.mod(it.clicks, ticker)
            if (ticker == 0) zeros++
        }
    println(zeros)
}




