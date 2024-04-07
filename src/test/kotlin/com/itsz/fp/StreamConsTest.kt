package com.itsz.fp

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StreamConsTest {

    @Test
    fun of() {
        val intStream = Stream.of(1, 2, 2, 4, 9, 3)
        val list = intStream.toList()
        assertEquals(6, List.length(list))
    }

    @Test
    fun exists() {
        val intStream = Stream.of(1, 2, 2, 4, 9, 3)
        assertTrue(intStream.exists { it > 5 })
        assertFalse(intStream.exists { it > 10 })
    }

    @Test
    fun exists2() {
        val intStream = Stream.of(1, 2, 2, 4, 9, 3)
        assertTrue(intStream.exists2 { it > 5 })
        assertFalse(intStream.exists2 { it > 10 })
    }

    @Test
    fun forAll() {
        val intStream = Stream.of(1, 2, 2, 4, 9, 3)
        assertTrue(intStream.forAll { it < 10 })
        assertTrue(intStream.forAll { it >= 1 })
    }

}