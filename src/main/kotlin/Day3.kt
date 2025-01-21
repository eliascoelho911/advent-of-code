import util.InputReader

private val input = InputReader.readText("day3_input.txt")

private val muls by lazy {
    val mulRegex = Regex("mul\\(\\d+,\\d+\\)")
    mulRegex.findAll(input)
}

private val dos by lazy {
    val doRegex = Regex("do\\(\\)")
    doRegex.findAll(input)
}

private val donts by lazy {
    val dontRegex = Regex("don't\\(\\)")
    dontRegex.findAll(input)
}

fun main() {
    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}

private fun part1(): Int {
    val result = muls.sumOf { mul ->
        multiplyMul(mul)
    }
    return result
}

private fun part2(): Int {
    fun mulIsEnabled(mul: MatchResult): Boolean {
        val lastDontBeforeMul = donts.lastOrNull { it.range.last < mul.range.first }?.range?.last ?: return true
        val lastDoBeforeMul = dos.lastOrNull { it.range.last < mul.range.first }?.range?.last ?: return false
        return lastDoBeforeMul > lastDontBeforeMul
    }

    return muls.sumOf { mul ->
        if (mulIsEnabled(mul)) {
            multiplyMul(mul)
        } else 0
    }
}

private fun multiplyMul(mul: MatchResult) = mul.value.substring(4, mul.value.length - 1)
    .split(",")
    .map { it.toInt() }
    .reduce { acc, s -> acc * s }