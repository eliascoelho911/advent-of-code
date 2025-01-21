package util

object InputReader {
    fun readText(name: String): String {
        val classLoader = Thread.currentThread().contextClassLoader
        return classLoader.getResource(name)?.readText()!!
    }

    fun readLines(name: String): List<String> {
        return readText(name).lines()
    }

    fun readTwoLongColumns(name: String): Pair<List<Long>, List<Long>> {
        val firstColumn = mutableListOf<Long>()
        val secondColumn = mutableListOf<Long>()

        readLines(name).map { line ->
            line.split(" ").let {
                firstColumn.add(it.first().toLong())
                secondColumn.add(it.last().toLong())
            }
        }

        return Pair(firstColumn, secondColumn)
    }

    fun readIntColumns(name: String): List<List<Int>> {
        val digitRegex = Regex("\\d+")
        return buildList<MutableList<Int>> {
            readLines(name).forEach { line ->
                val matchResultSequence = digitRegex.findAll(line)
                matchResultSequence.forEachIndexed { index, matchResult ->
                    val column = getOrNull(index)
                    if (column == null) {
                        add(index, mutableListOf(matchResult.value.toInt()))
                    } else {
                        column.add(matchResult.value.toInt())
                    }
                }
            }
        }
    }

    fun readIntLines(name: String): List<List<Int>> {
        val digitRegex = Regex("\\d+")
        return readLines(name).map { line ->
            val matchResultSequence = digitRegex.findAll(line)
            matchResultSequence.map { it.value.toInt() }.toList()
        }
    }
}