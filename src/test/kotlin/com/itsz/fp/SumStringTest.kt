package com.itsz.fp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SumStringTest{

    private lateinit var sumString: SumString

    @BeforeEach
    fun init() {
        sumString = SumString()
    }

    @Test
    fun sumString(){
       val sum =  sumString.sum("102", "201")
        assertEquals("303", sum)

    }
}