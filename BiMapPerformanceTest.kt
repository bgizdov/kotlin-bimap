package utils

import client.BiMap
import kotlinx.datetime.Clock
import kotlin.test.Test

class BiMapPerformanceTest {
    private fun measureTime(block: () -> Unit): Long {
        val start = Clock.System.now()
        block()
        val end = Clock.System.now()
        return end.toEpochMilliseconds() - start.toEpochMilliseconds()
    }

    @Test
    fun `test performance`() {
        val sizes = listOf(100, 1000, 10000)

        sizes.forEach { size ->
            println("\nTesting with size: $size")

            // Setup
            val biMap = BiMap<Int, String>()

            // Test insertion
            val insertTime = measureTime {
                repeat(size) { i ->
                    biMap[i] = "value$i"
                }
            }
            println("Insert time: $insertTime ms")

            // Test lookups
            val lookupTime = measureTime {
                repeat(size) { i ->
                    biMap[i]
                }
            }
            println("Lookup time: $lookupTime ms")

            // Test inverse lookups
            val inverseLookupTime = measureTime {
                val inverse = biMap.inverse
                repeat(size) { i ->
                    inverse["value$i"]
                }
            }
            println("Inverse lookup time: $inverseLookupTime ms")

            // Test removal
            val removalTime = measureTime {
                repeat(size) { i ->
                    biMap.remove(i)
                }
            }
            println("Remove time: $removalTime ms")
        }
    }
}