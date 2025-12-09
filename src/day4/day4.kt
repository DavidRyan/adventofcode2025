import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readText().trim().split("\n")
    println(input)
    println(Day4(input.map { it.toCharArray() }).part2())
}

class Day4(val input: List<CharArray>) {
    fun part2(): Int {
        fun search(input: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
            val queue = ArrayDeque<Pair<Int, Int>>()

            queue.add(x + 1 to y)
            queue.add(x - 1 to y)
            queue.add(x to y + 1)
            queue.add(x to y - 1)
            queue.add(x + 1 to y + 1)
            queue.add(x - 1 to y - 1)
            queue.add(x + 1 to y - 1)
            queue.add(x - 1 to y + 1)

            var counter = 0
            while (queue.isNotEmpty()) {
                val node = queue.removeFirst()
                if (node.first < 0 || node.first > input[0].size - 1 || node.second < 0 || node.second > input.size - 1) {
                    //todo
                } else if (input[node.first][node.second] == '@') {
                    counter++
                }
            }
            return counter
        }

        fun searchAndRemove(mutableList: MutableList<MutableList<Char>>): Int {
            var count = 0
            val newInput = mutableList.toMutableList()
            mutableList.forEachIndexed { x, chars ->
                chars.forEachIndexed { y, c ->



                    if (c == '@') {
                        if (search(mutableList, x, y) < 4) {
                            count++
                            newInput[x][y] = '.'
                        }
                    }
                }
            }
            if (count == 0) return 0
            return count  + searchAndRemove(newInput)
        }
        return searchAndRemove(input.map { it.toMutableList() }.toMutableList())
    }


    fun part1(): Int {
        var count = 0
        fun search(input: List<CharArray>, x: Int, y: Int, depth: Int): Int {
            val queue = ArrayDeque<Pair<Int, Int>>()

            queue.add(x + 1 to y)
            queue.add(x - 1 to y)
            queue.add(x to y + 1)
            queue.add(x to y - 1)
            queue.add(x + 1 to y + 1)
            queue.add(x - 1 to y - 1)
            queue.add(x + 1 to y - 1)
            queue.add(x - 1 to y + 1)

            var counter = 0
            while (queue.isNotEmpty()) {
                val node = queue.removeFirst()
                if (node.first < 0 || node.first > input[0].size - 1 || node.second < 0 || node.second > input.size - 1) {
                    //todo
                } else if (input[node.first][node.second] == '@') {
                    counter++
                }
            }
            return counter
        }
        input.forEachIndexed { x, chars ->
            chars.forEachIndexed { y, c ->
                if (c == '@') {
                    if (search(input, x, y, 1) < 4) {
                        count++
                    }
                }
            }
        }
        return count
    }
}

