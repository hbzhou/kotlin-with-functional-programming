package com.itsz.fp

import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldHaveSize
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StreamConsTest {

    @Test
    fun of() {
        val intStream = Stream.of(1, 2, 2, 4, 9, 3)
        val list = intStream.toList()

        val listOf = listOf(1, 2, 3, 4, 5)
        listOf shouldHaveSize 3
    }


    @Test
    fun exists() {
        val intStream = Stream.of(1, 2, 2, 4, 9, 3)
        intStream.exists { it>5 } shouldBe true
        intStream.exists { it>10 } shouldBe false
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