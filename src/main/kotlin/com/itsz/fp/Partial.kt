package com.itsz.fp

object Partial {

    fun <A, B, C> partial(a: A, f: (A, B) -> C): (B) -> C = {
        b: B -> f(a, b)
    }
}