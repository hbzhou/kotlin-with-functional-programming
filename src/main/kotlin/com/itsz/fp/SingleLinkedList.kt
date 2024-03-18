package com.itsz.fp

sealed class List<out A> {

    companion object {
        private fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

    }

    fun <A> tail(xs: List<A>): List<A> = when (xs) {
        is Nil -> throw IllegalStateException("don't have tail")
        is Cons -> xs.tail
    }

    fun <A> setHead(xs: List<A>, x: A): List<A> = when (xs) {
        is Nil -> throw IllegalStateException("don't have head")
        is Cons -> Cons(x, xs.tail)
    }

    tailrec fun <A> drop(l: List<A>, n: Int): List<A> =
        if (n == 0) l
        else when (l) {
            is Cons -> drop(l.tail, n - 1)
            is Nil -> throw IllegalStateException("Cannot drop more elements than in list")
        }

    tailrec fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> = when (l) {
        is Nil -> l
        is Cons -> if (f(l.head)) dropWhile(l.tail, f) else l
    }

    fun <A> init(l: List<A>): List<A> = when (l) {
        is Cons -> if (l.tail is Nil) Nil else Cons(l.head, init(l.tail))
        is Nil -> throw IllegalStateException("")
    }


    private fun sum(ints: List<Int>): Int = when (ints) {
        is Nil -> 0
        is Cons -> ints.head + sum(ints.tail)
    }

    private fun product(doubles: List<Double>): Double = when (doubles) {
        is Nil -> 0.0
        is Cons -> doubles.head + product(doubles.tail)
    }
}


data object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()


fun main() {
    val ex1: List<Double> = Nil
    val ex2: List<Int> = Cons(1, Nil)
    val ex3: List<String> = Cons("a", Cons("b", Nil))

}