import kotlin.math.abs
import util.InputReader

fun main(args: Array<String>) {
    val reports = InputReader.readIntLines("day2_input.txt")
    var partOneSafeReports = 0
    var partTwoSafeReports = 0

    reports.forEach report@{ report ->
        var lastLevel: Int? = null
        var reportIsDecreasing: Boolean? = null
        var hasUnsafeAlert = false

        report.forEachIndexed level@{ index, currentLevel ->
            if (lastLevel == null) {
                lastLevel = currentLevel
                return@level
            }

            val diff = currentLevel - lastLevel!!
            val absDiff = abs(diff)
            val currentLevelIsDecreasing = (diff < 0).takeIf { absDiff > 0 }

            if (absDiff < 1 || absDiff > 3) {
                if (!hasUnsafeAlert) {
                    lastLevel = currentLevel
                    if (absDiff > 3 && index > 1) return@report
                    hasUnsafeAlert = true
                    return@level
                } else return@report
            }

            if (currentLevelIsDecreasing != null && reportIsDecreasing != null && reportIsDecreasing != currentLevelIsDecreasing) {
                if (!hasUnsafeAlert) {
                    hasUnsafeAlert = true
                    if (index != 1) lastLevel = currentLevel
                    if (index == 2) reportIsDecreasing = null
                    return@level
                } else return@report
            }

            lastLevel = currentLevel
            reportIsDecreasing = currentLevelIsDecreasing
        }

        if (!hasUnsafeAlert) partOneSafeReports++
        partTwoSafeReports++
    }

    println("Part One: $partOneSafeReports")
    println("Part Two: $partTwoSafeReports")
}