package com.itsz.fp

import com.itsz.fp.List as List

sealed class Stream<out A>{
    companion object {
        fun <A> of(vararg args: A): Stream<A> {
            return if (args.isEmpty()) Empty
            else StreamCons({ args[0] }, { of(*args.sliceArray(1 until args.size)) })
        }

    }
}

data class StreamCons<out A>(
    val header: () -> A,
    val tail: () -> Stream<A>
) : Stream<A>()

data object Empty : Stream<Nothing>()


fun <A> streamCons(header: () -> A, tail: () -> Stream<A>): Stream<A> {
    val hd: A by lazy(header)
    val tl: Stream<A> by lazy(tail)
    return StreamCons({ hd }, { tl })
}

fun <A> empty(): Stream<A> = Empty

fun <A> of(vararg args: A): Stream<A> {
    return if (args.isEmpty()) Empty
    else StreamCons({ args[0] }, { of(*args.sliceArray(1 until args.size)) })
}

fun <A> Stream<A>.toList(): List<A> = when(this){
    is Empty -> Nil
    is StreamCons -> {
        val header: A by lazy(this.header)
        val tail: Stream<A> by lazy(this.tail)
        Cons(header, tail.toList())
    }
}

fun <A> Stream<A>.take(n: Int): Stream<A> {
    fun go(xs: Stream<A>, i: Int): Stream<A> = when(xs){
        is Empty -> empty()
        is StreamCons -> if (i == 0 ) empty() else StreamCons(header = xs.header, tail = { go(xs, i -1)})
    }
    return go(this, n)
}


fun <A> Stream<A>.drop(n: Int): Stream<A> {
   tailrec fun go(xs: Stream<A>, i: Int): Stream<A> = when(xs){
        is Empty -> empty()
        is StreamCons -> if (i == 0) xs else go(xs.tail(), i -1)
    }
    return go(this, n)
}

fun <A> Stream<A>.takeWhile(p: (A) -> Boolean): Stream<A> = when(this){
    is Empty -> empty()
    is StreamCons -> if (p(this.header())) this.tail().takeWhile(p) else this
}

fun <A> Stream<A>.takeWhile2(p: (A) -> Boolean): Stream<A> = foldRight(
    { empty() },
    { head, tail -> if (p(head)) StreamCons({head}, tail) else tail()}
)


fun <A> Stream<A>.exists(p: (A) -> Boolean): Boolean = when(this){
    is Empty -> false
    is StreamCons -> if (p(this.header())) true else this.tail().exists(p)
}

fun <A> Stream<A>.exists2(p: (A) -> Boolean): Boolean = foldRight( { false }, { a, function -> p(a) || function() })

fun <A> Stream<A>.forAll(p: (A) -> Boolean): Boolean = foldRight({ true }, { a, function -> p(a) && function() })

fun <A, B> Stream<A>.map(f: (A) -> B): Stream<B> = foldRight({ empty() }, { h, t -> StreamCons({ f(h) }, t) })

fun <A> Stream<A>.filter(f: (A) -> Boolean): Stream<A> = foldRight({ empty() }, { h, t -> if (f(h)) StreamCons({ h }, t) else t() })

fun <A> Stream<A>.append(sa: () -> Stream<A>): Stream<A> = foldRight(sa) { h, t -> StreamCons({ h }, t) }

fun <A, B> Stream<A>.flatMap(f: (A) -> Stream<B>): Stream<B> = foldRight({ empty() }, { h, t -> f(h).append(t) })

fun <A, B> Stream<A>.foldRight(z: () -> B, f: (A, () -> B) -> B): B = when (this) {
    is StreamCons<A> -> f(header()) {
        tail().foldRight(z, f)
    }
    is Empty -> z()
}

