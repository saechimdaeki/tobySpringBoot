package me.saechimdaeki.tobyspringboot.simple

interface HelloService {
    fun sayHello(name: String?) : String?
    fun countOf(name: String) : Int
}