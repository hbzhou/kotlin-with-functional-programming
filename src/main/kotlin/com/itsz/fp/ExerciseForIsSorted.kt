package com.itsz.fp



object ExerciseForIsSorted {

    private val <T>  kotlin.collections.List<T>.tail: kotlin.collections.List<T>
        get() = drop(1)

    private val <T>  kotlin.collections.List<T>.head: T
        get() = first()

    fun <A> isSorted(aa:  kotlin.collections.List<A>, order: (A, A) -> Boolean): Boolean {
        fun go(a:A, list:  kotlin.collections.List<A>): Boolean =
            if (list.isEmpty()) true
            else if (!order(a, list.head)) false
            else go(list.head, list.tail)

        return aa.isEmpty() || go(aa.head, aa.tail)
    }

}