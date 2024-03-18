package com.itsz.fp

object Curry {

    fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = {
        a: A -> { b: B -> f(a, b) }
    }

    fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C = {
       a: A, b: B -> f(a)(b)
    }

    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = {
        a: A -> f(g(a))
    }


}

fun main() {
    val toUpperCase = { str: String -> str.uppercase()}
    val concatQuestionMark = {str: String -> str.plus("?")}

    val compose = Curry.compose(toUpperCase, concatQuestionMark)
    println(compose("What's your Name"))

    val add = { a: Int, b: Int -> a + b }
    val curry = Curry.curry(add)
    println(curry(3)(4))

    val uncurry = Curry.uncurry(curry)
    println(uncurry(3, 4))

}