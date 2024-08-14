package com.itsz.fp

import kotlin.collections.List as KotlinList

object Curry {

    fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = { a: A ->
        { b: B -> f(a, b) }
    }

    fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C = { a: A, b: B ->
        f(a)(b)
    }

    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { a: A ->
        f(g(a))
    }


}

fun main() {
    val compose = Curry.compose(toUpperCase, concatQuestionMark)
    println(compose("What's your Name"))

    val add = { a: Int, b: Int -> a + b }
    val curry = Curry.curry(add)
    println(curry(3)(4))

    val uncurry = Curry.uncurry(curry)
    println(uncurry(3, 4))

    val oddLength = Curry.compose(isOdd, length)
    val oddLengthMethodRef = Curry.compose(::isOdd, ::length)
    println(listOf("a", "addafd", "jeremy", "1231231").filter(oddLength))
    println(listOf("a", "addafd", "jeremy", "1231231").filter(oddLengthMethodRef))
    println(listOf("abc", "addafd", "jeremy", "dd1231231").filterOddLength())
}

val toUpperCase = { str: String -> str.uppercase() }
val concatQuestionMark = { str: String -> str.plus("?") }
val isOdd: (Int) -> Boolean = { it % 2 != 0 }
val length: (String) -> Int = { s -> s.length }

fun isOdd(x: Int) = x % 2 != 0
fun length(str: String) = str.length

fun String.cap() = replaceFirstChar { it.lowercase() }

fun KotlinList<String>.filterOddLength(): KotlinList<String> =
    filter(Curry.compose(isOdd, length)).map(toUpperCase).map(concatQuestionMark)
        .map(String::cap)

