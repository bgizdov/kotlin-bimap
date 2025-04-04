package utils

import client.BiMap
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BiMapTest {
    @Test
    fun `test empty BiMap`() {
        val biMap = BiMap<String, Int>()
        assertTrue(biMap.keys.isEmpty())
        assertTrue(biMap.values.isEmpty())
    }

    @Test
    fun `test set and get`() {
        val biMap = BiMap<String, Int>()
        biMap["one"] = 1
        biMap["two"] = 2

        assertEquals(1, biMap["one"])
        assertEquals(2, biMap["two"])
        assertNull(biMap["three"])
    }

    @Test
    fun `test remove`() {
        val biMap = BiMap<String, Int>()
        biMap["one"] = 1
        biMap["two"] = 2

        assertEquals(1, biMap.remove("one"))
        assertNull(biMap["one"])
        assertEquals(2, biMap["two"])
    }

    @Test
    fun `test inverse map`() {
        val biMap = BiMap<String, Int>()
        biMap["one"] = 1
        biMap["two"] = 2

        val inverse = biMap.inverse
        assertEquals("one", inverse[1])
        assertEquals("two", inverse[2])
    }

    @Test
    fun `test value overwrite`() {
        val biMap = BiMap<String, Int>()
        biMap["one"] = 1
        biMap["one"] = 2

        assertEquals(2, biMap["one"])
        assertEquals("one", biMap.inverse[2])
        assertNull(biMap.inverse[1])
    }

    @Test
    fun `test keys and values`() {
        val biMap = BiMap<String, Int>()
        biMap["one"] = 1
        biMap["two"] = 2

        assertEquals(setOf("one", "two"), biMap.keys)
        assertEquals(setOf(1, 2), biMap.values)
    }
}