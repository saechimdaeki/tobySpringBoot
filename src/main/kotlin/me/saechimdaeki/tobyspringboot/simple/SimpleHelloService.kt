package me.saechimdaeki.tobyspringboot.simple

import org.springframework.stereotype.Service

//@Service
@MyComponent
class SimpleHelloService : HelloService {
    override fun sayHello(name: String) = "Hello $name"
}