package me.saechimdaeki.tobyspringboot

import org.springframework.stereotype.Service

@Service
class SimpleHelloService : HelloService {
    override fun sayHello(name: String) = "Hello $name"
}