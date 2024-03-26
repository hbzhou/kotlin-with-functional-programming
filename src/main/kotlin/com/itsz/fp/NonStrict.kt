package com.itsz.fp


fun <A> lazyIf(
    cond: Boolean,
    onTrue: () -> A,
    onFalse: () -> A
): A = if (cond) onTrue() else onFalse()

fun maybeTwice(b: Boolean, i: () -> Int) = if (b) i() + i() else 0

fun maybeTwice2(b: Boolean, i: () -> Int) = run {
    val j : Int by lazy(i)
    if (b) j + j else 0
}