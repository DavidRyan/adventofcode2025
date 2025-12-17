import java.io.File
import java.util.Stack

fun main(args: Array<String>) {
    val input = File(args[0]).readText().trim().lines()
    Day7(input.map { it.toMutableList()}.toMutableList()).part1()
}

class Day7(val input: MutableList<MutableList<Char>>) {
    data class Node(val x: Int, val y: Int)

    fun MutableList<MutableList<Char>>.getNode(node: Node) : Char {
        return this[node.y][node.x]
    }

    fun part2() {

        fun validate(node: Node) : Boolean {
            return node.x < input[0].size && node.x >= 0 &&
                    node.y < input.size && node.y >= 0
        }

        val cache = mutableMapOf<Pair<Int, Int>, Long>()

        fun count(node: Node) : Long {
            if (cache.contains(node.x to node.y)) {
                return cache.get(node.x to node.y)!!
            }
            if (!validate(node)) return 1
            var result = 0L
            if (input.getNode(node) == '^') {
                val left = Node(x = node.x-1, y = node.y)
                val right = Node(x = node.x+1, y = node.y)
                result = count(left) + count(right)
            } else {
                result = count(Node(x = node.x, y = node.y+1))
            }
            cache[node.x to node.y] = result
            return result
        }




        input.forEachIndexed { y, chars ->
            chars.forEachIndexed { x, c ->
                if (c == 'S') {
                    println(count(Node(x, y)))
                }
            }
        }
    }

    fun part1() {

        fun dfs(parent: Node?, node: Node, visited: MutableSet<Node>): Int {
            val valid = node.x < input[0].size && node.x >= 0 &&
                    node.y < input.size && node.y >= 0
            if (visited.contains(node)) return 0
            if (valid) {
                visited.add(node)
                if (input.getNode(node) == '^') {
                    return 1 + dfs(node, Node(x = node.x-1, y = node.y), visited) +
                            dfs(node, Node(x = node.x+1, y = node.y), visited)
                } else {
                    return dfs(parent, node.copy(y = node.y+1), visited)
                }
            }
            return 0
        }

        input.forEachIndexed { y, chars ->
            chars.forEachIndexed { x, c ->
                if (c == 'S') {
                    val count = dfs(null, node = Node(x, y+1), emptySet<Node>().toMutableSet())
                    println(count)
                }
            }
        }
    }

}
