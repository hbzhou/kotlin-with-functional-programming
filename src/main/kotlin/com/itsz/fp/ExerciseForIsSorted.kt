package com.itsz.fp

object ExerciseForIsSorted {

    private val <T> List<T>.tail: List<T>
        get() = drop(1)

    private val <T> List<T>.head: T
        get() = first()

    fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
        fun go(a:A, list: List<A>): Boolean =
            if (list.isEmpty()) true
            else if (!order(a, list.head)) false
            else go(list.head, list.tail)

        return aa.isEmpty() || go(aa.head, aa.tail)
    }

}