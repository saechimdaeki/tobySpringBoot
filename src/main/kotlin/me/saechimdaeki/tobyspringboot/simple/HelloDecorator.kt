package me.saechimdaeki.tobyspringboot.simple

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class HelloDecorator(private val helloService: HelloService) : HelloService {
    override fun sayHello(name: String?): String {
        return "* ${helloService.sayHello(name)} *"
    }

    override fun countOf(name: String): Int {
        return helloService.countOf(name)
    }

}