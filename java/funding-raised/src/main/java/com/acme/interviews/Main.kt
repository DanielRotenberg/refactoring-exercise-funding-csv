package com.acme.interviews

fun main() {
    println("running main")
    listOf(1,2,3).filter { it % 2 == 0 }.forEach(::println)
}