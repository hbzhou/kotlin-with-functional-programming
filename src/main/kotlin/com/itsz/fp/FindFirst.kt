package com.itsz.fp

object FindFirst {


    fun <T> findFirst(array: Array<T>, predicate: (T) -> Boolean): Int {
        tailrec fun loop(n: Int): Int = when {
                array.size < n -> -1
                predicate(array[n])-> n
                else -> loop(n + 1)
            }
        return loop(0)
    }
}

fun main() {
    println(FindFirst.findFirst(arrayOf("jeremy", "jackson", "jacob")) { it == "jackson" })
}