package com.itsz.fp

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class SumStringTest {

    private lateinit var sumString: SumString

    @BeforeEach
    fun init() {
        sumString = SumString()
    }

    @Test
    fun sumString() {
        val sum = sumString.sum("102", "201")
        assertEquals("303", sum)

    }

    @Test
    fun `test int array of minus ones`() {
        val sum = arrayOfMinusOnes(8).sum()
        sum shouldBe -8
    }

    @Test
    fun `test files nio api`() {
        val stream = Files.newInputStream(Paths.get("c://demo.txt"))
        stream.buffered().reader().use {
            it.readText()
        }
    }

    @Test
    fun `swap two values`() {
        var a = 2
        var b = 3
        a = b.also { b = a }

        a shouldBe 3
        b shouldBe 2
    }

    @Test
    fun `bitwise test`(){
        val result = (1 shr 2) and 0x000FF000
        result shouldBe 0
    }

    @Test
    fun `init  array` (){
        val array = Array(2) { i -> i * i }
        println(array.contentToString())

        val arrays = Array(3) { Array(2) { 0 } }
        println(arrays.contentToString())
    }
}

fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }

}