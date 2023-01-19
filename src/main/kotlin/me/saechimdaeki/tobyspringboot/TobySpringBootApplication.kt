package me.saechimdaeki.tobyspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TobySpringBootApplication

fun main(args: Array<String>) {
    runApplication<TobySpringBootApplication>(*args)
}
