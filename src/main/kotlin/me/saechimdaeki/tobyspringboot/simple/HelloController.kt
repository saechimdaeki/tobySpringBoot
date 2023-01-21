package me.saechimdaeki.tobyspringboot.simple

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(val simpleHelloService: HelloService) {

    /*
        토비님이 사용하시는 Httpie를 사용하면 http -v ":8080/hello?name=Spring" 이렇게 요청할 수 있음.
     */
    @GetMapping("/hello")
    fun hello(name: String?): String {
        name?.let {
            return simpleHelloService.sayHello(name)
        }
        throw RuntimeException("name은 null")
    }
}