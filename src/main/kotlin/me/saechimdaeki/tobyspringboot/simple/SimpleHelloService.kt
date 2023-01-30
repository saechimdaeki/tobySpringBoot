package me.saechimdaeki.tobyspringboot.simple

import org.springframework.stereotype.Service

//@Service
@MyComponent
class SimpleHelloService(val helloRepository: HelloRepository) : HelloService {

    override fun sayHello(name: String?):String {
        this.helloRepository.increaseCount(name)

        return "Hello $name"
    }

    override fun countOf(name: String): Int {
        return helloRepository.countOf(name)
    }

}