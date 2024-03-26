package com.itsz.fp

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TreeTest {
    @Test
    fun testSize() {
        val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
        assertEquals(5, size(tree))
    }

    @Test
    fun testSizeF() {
        val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
        assertEquals(5, sizeF(tree))
    }


    @Test
    fun testMaximum() {
        val tree = Branch(Branch(Leaf(1), Leaf(9)), Branch(Branch(Leaf(4), Leaf(5)), Leaf(8)))
        assertEquals(9, maximum(tree))
    }

    @Test
    fun testMaximumF() {
        val tree = Branch(Branch(Leaf(1), Leaf(9)), Branch(Branch(Leaf(4), Leaf(5)), Leaf(8)))
        assertEquals(9, maximumF(tree))
    }

    @Test
    fun testDepth() {
        val tree = Branch(Branch(Leaf(1), Leaf(9)), Branch(Branch(Leaf(4), Leaf(5)), Leaf(8)))
        assertEquals(3, depth(tree))

    }

    @Test
    fun testDepthF() {
        val tree = Branch(Branch(Leaf(1), Leaf(9)), Branch(Branch(Leaf(4), Leaf(5)), Leaf(8)))
        assertEquals(3, depthF(tree))

    }

    @Test
    fun testMap(){
        val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
        val mapTree = map(tree) { x -> x * 2 }
        println(mapTree)
    }

    @Test
    fun testMapF(){
        val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
        val mapTree = mapF(tree) { x -> x * 2 }
        println(mapTree)
    }
}

