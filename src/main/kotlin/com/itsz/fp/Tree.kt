package com.itsz.fp

sealed class Tree<out A>

data class Leaf<A>(val value: A) : Tree<A>()

data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()

fun <A> size(tree: Tree<A>): Int = when (tree) {
    is Leaf -> 1
    is Branch -> 1 + size(tree.left) + size(tree.right)
}

fun maximum(tree: Tree<Int>): Int = when (tree) {
    is Leaf -> tree.value
    is Branch -> maxOf(maximum(tree.left), maximum(tree.right))
}

fun <A> depth(tree: Tree<A>): Int = when (tree) {
    is Leaf -> 0
    is Branch -> 1 + maxOf(depth(tree.left), depth(tree.right))
}

fun <A, B> map(tree: Tree<A>, f: (A) -> B): Tree<B> = when (tree) {
    is Leaf -> Leaf(f(tree.value))
    is Branch -> Branch(map(tree.left, f), map(tree.right, f))
}

fun <A, B> fold(tree: Tree<A>, l: (A) -> B, b: (B, B) -> B): B = when (tree) {
    is Leaf -> l(tree.value)
    is Branch -> b(fold(tree.left, l, b), fold(tree.right, l, b))
}

fun <A> sizeF(tree: Tree<A>): Int = fold(tree, { 1 }, { b1, b2 -> 1 + b1 + b2 })

fun maximumF(tree: Tree<Int>): Int = fold(tree, { a -> a }, { b1, b2 -> maxOf(b1, b2) })

fun <A> depthF(tree: Tree<A>): Int = fold(tree, { 0 }, { b1, b2 -> 1 + maxOf(b1, b2) })

fun <A, B> mapF(tree: Tree<A>, f: (A) -> B): Tree<B> =
    fold(tree, { a -> Leaf(f(a)) }, { b: Tree<B>, b2: Tree<B> -> Branch(b, b2) })





