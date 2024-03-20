package com.itsz.fp

sealed class List<out A> {

    companion object {
        private fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        private fun sum(ints: List<Int>): Int = when (ints) {
            is Nil -> 0
            is Cons -> ints.head + sum(ints.tail)
        }

        private fun product(doubles: List<Double>): Double = when (doubles) {
            is Nil -> 0.0
            is Cons -> doubles.head + product(doubles.tail)
        }

        private fun <A> tail(xs: List<A>): List<A> = when (xs) {
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

        private tailrec fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> = when (l) {
            is Nil -> l
            is Cons -> if (f(l.head)) dropWhile(l.tail, f) else l
        }

        fun <A> init(l: List<A>): List<A> = when (l) {
            is Cons -> if (l.tail is Nil) Nil else Cons(l.head, init(l.tail))
            is Nil -> throw IllegalStateException("")
        }

        private fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B = when (xs) {
            is Nil -> z
            is Cons -> f(xs.head, foldRight(xs.tail, z, f))
        }

        private tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B = when (xs) {
            is Nil -> z
            is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
        }

        fun <A> length(xs: List<A>): Int = when (xs) {
            is Nil -> 0
            is Cons -> foldRight(xs.tail, 1) { _, acc -> acc + 1 }
        }

        fun <A> length1(xs: List<A>): Int = foldLeft(xs, 0) { acc, _ -> acc + 1 }

        fun sum1(xs: List<Int>): Int = foldLeft(xs, 0) { a, b -> a + b }

        fun multi(xs: List<Int>): Int = foldLeft(xs, 1) { a, b -> a * b }
    }

}


data object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()


fun main() {
    val nil = Nil
    val list = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    println(List.length(nil))
    println(List.length(list))
    println(List.length(nil))
    println(List.length1(list))
    println(List.sum1(list))
    println(List.multi(list))

}