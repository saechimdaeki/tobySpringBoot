package me.saechimdaeki.tobyspringboot

import me.saechimdaeki.tobyspringboot.simple.HelloRepository
import me.saechimdaeki.tobyspringboot.simple.HelloService
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class HelloServiceCountTest @Autowired constructor(
    val helloService: HelloService,
    val helloRepository: HelloRepository
) {

    @Test
    fun sayHelloIncreaseCount() {

        repeat(10) {
            helloService.sayHello("saechimdaeki")
            assertThat(helloRepository.countOf("saechimdaeki")).isEqualTo(it+1)
        }
    }
}