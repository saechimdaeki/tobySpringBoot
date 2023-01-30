package me.saechimdaeki.tobyspringboot.simple

interface HelloRepository {

    fun findHello(name: String?) : Hello?

    fun increaseCount(name: String?) : Unit

    fun countOf(name: String):Int {
        return findHello(name)?.count ?: 0
    }
}