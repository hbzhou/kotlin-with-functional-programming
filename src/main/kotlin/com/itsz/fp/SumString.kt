package com.itsz.fp

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class SumString {

    fun sum(num1: String, num2: String) {
        val num1CharArr = num1.toCharArray()
        val num2CharArr = num2.toCharArray()
    }
}

fun calculate(a: Int, b: Int, op: (Int, Int) -> Int): Int {
    return op(a, b)
}


fun sumFun(a: Int, b: Int): Int {
    return a + b
}

data class Item(val name: String, val price: Float)

data class Order(val items: Collection<Item>)

fun Order.maxPricedItemValue(): Float = items.maxByOrNull { it.price }?.price ?: 0f
fun Order.maxPricedItemName(): String = items.maxByOrNull { it.price }?.name ?: "Jeremy Prime"

val Order.commaDelimitedItemNames: String
    get() = items.joinToString(separator = ";") { it.name }

val A = listOf("a", "b", "c")
val B = listOf(1, 2, 3, 4)

interface SoundBehavior { // 1
    fun makeSound()
}

class ScreamBehavior(val name: String) : SoundBehavior {
    override fun makeSound() = println("${name.uppercase()} !!!")
}

class RockAndRollBehavior(val name: String) : SoundBehavior {
    override fun makeSound() = println("I'm The King of Rock 'N' Roll: $name")
}

class TomAraya(n: String) : SoundBehavior by ScreamBehavior(n)

class ElvisPresley(n: String) : SoundBehavior by RockAndRollBehavior(n)              // 3

class User(map: Map<String, Any?>) {
    val name: String by map                // 1
    val age: Int by map                // 1
}

class Util(val objectMapper: ObjectMapper) {
    inline fun <reified T> testGeneric(content: String): T {
        return objectMapper.readValue(content)
    }
}

data class Car(var color: String? = null, var type: String? = null)

fun main() {
    println(calculate(3, 4) { a, b -> a + b })
    println(calculate(3, 4, ::sumFun))

    val order = Order(listOf(Item("Bread", 25.0F), Item("Wine", 29.0F), Item("Water", 12.0F)))
    println(order.maxPricedItemName())
    println(order.maxPricedItemValue())
    println(order.commaDelimitedItemNames)

    println(A zip B)
    println(A.zip(B) { a, b -> "$a$b" })

    val user = User(mapOf("name" to "Jeremy Prime", "age" to 25.0F))
    println("name = ${user.name}, age = ${user.age}")
    val json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }"

    val car = Util(ObjectMapper()).testGeneric<Car>(json)
    println(car)

    TomAraya("Thrash Metal").makeSound()
    ElvisPresley("Dancin' to the Jailhouse Rock.").makeSound()
}