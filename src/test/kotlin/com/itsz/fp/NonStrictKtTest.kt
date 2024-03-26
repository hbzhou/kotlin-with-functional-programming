package com.itsz.fp

import org.junit.jupiter.api.Test

class NonStrictKtTest{

    @Test
    fun testLazyIf() {
        lazyIf(true, { println(2) }, { println(3) })
    }

    @Test
    fun testMaybeTwice(){
        maybeTwice(true) { println("hi"); 1 + 2 }
    }

    @Test
    fun testMaybeTwice2(){
        maybeTwice2(true) { println("hi"); 1 + 2 }
    }
}