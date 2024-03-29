package com.itsz.fp

sealed class Option <out A>
data class Some<out A>(val get: A) : Option<A>()
data object None: Option<Nothing>()

fun <A, B> Option<A>.map(f: (A) -> B): Option<B> = when(this){
     is None -> None
     is Some -> Some(f(this.get))
}

fun <A, B> Option<A>.flatMap(f: (A) -> Option<B>): Option<B> = when(this){
    is None -> None
    is Some -> f(this.get)
}

fun <A> Option<A>.getOrElse(default: () -> A): A = when(this) {
    is None -> default()
    is Some -> this.get
}

fun <A> Option<A>.orElse(ob: () -> Option<A>): Option<A> = when(this){
     is None -> ob()
     is Some -> this
}

fun <A> Option<A>.filter(f: (A) -> Boolean): Option<A> = when(this){
     is None -> None
     is Some -> if (f(this.get)) this else None
}
