package me.saechimdaeki.tobyspringboot.simple

import org.springframework.stereotype.Service

@Service
class SimpleHelloService : HelloService {
    override fun sayHello(name: String) = "Hello $name"
}