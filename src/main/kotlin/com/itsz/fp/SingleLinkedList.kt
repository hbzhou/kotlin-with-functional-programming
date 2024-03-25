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

        fun <A> empty(): List<A> = Nil


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

        fun reverse(xs: List<Int>): List<Int> = foldLeft(xs, empty()) { list, i ->
            Cons(i, list)
        }

        fun <A> append1(list1: List<A>, list2: List<A>) = foldRight(list1, list2) { i, list -> Cons(i, list) }

        fun <A> append2(list1: List<A>, list2: List<A>) = foldLeft(list2, list1) { list, a -> Cons(a, list) }

        fun increment(list: List<Int>) = foldRight(list, empty<Int>()) { i, newList -> Cons(i + 1, newList) }

        fun doubleToString(doubles: List<Double>) =
            foldRight(doubles, empty()) { d: Double, strList: List<String> -> Cons(d.toString(), strList) }

        fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> = foldRight(xs, empty()) { a, list -> Cons(f(a), list) }

        fun <A, B> flatMap(xa: List<A>, f: (A) -> List<B>): List<B> =
            foldRight(xa, empty()) { a, list -> append1(f(a), list) }

        fun <A> filter(xs: List<A>, f: (A) -> Boolean): List<A> =
            foldRight(xs, empty()) { a, list -> if (f(a)) Cons(a, list) else list }

        fun <A> zipWith(xa: List<A>, xb: List<A>, f: (A, A) -> A): List<A> =
            when (xa) {
                is Nil -> Nil
                is Cons -> when (xb) {
                    is Nil -> Nil
                    is Cons -> Cons(
                        f(xa.head, xb.head),
                        zipWith(xa.tail, xb.tail, f)
                    )
                }
            }

       private tailrec fun <A> startsWith(l1: List<A>, l2: List<A>): Boolean =
            when (l1) {
                is Nil -> l2 == Nil
                is Cons -> when (l2) {
                    is Nil -> true
                    is Cons ->
                        if (l1.head == l2.head) startsWith(l1.tail, l2.tail)
                        else false
                }
            }

        tailrec fun <A> hasSubsequence(xs: List<A>, sub: List<A>): Boolean =
            when (xs) {
                is Nil -> false
                is Cons ->
                    if (startsWith(xs, sub)) true
                    else hasSubsequence(xs.tail, sub)
            }

    }

}


data object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()


fun main() {
    val nil = Nil
    val list = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    val list1 = Cons(5, Cons(6, Cons(7, Cons(8, Nil))))
    val list2 = Cons(1, Cons(2, Nil))
    println(List.length(nil))
    println(List.length(list))
    println(List.length(nil))
    println(List.length1(list))
    println(List.sum1(list))
    println(List.multi(list))
    println(List.reverse(list))
    println(List.increment(list))
    println(List.filter(list) { x -> x > 2 })
    println(List.zipWith(list, list1) { a, b -> a + b })
    println(List.hasSubsequence(list, list2))
    
}