package me.saechimdaeki.tobyspringboot

import me.saechimdaeki.tobyspringboot.simple.SimpleHelloService
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class HelloServiceTest {

    @ParameterizedTest(name = "#{index} test value = {0}")
    @ValueSource(strings = ["spring", "saechimdaeki", "hello World", "toby"])
    fun simpleHelloService(name: String) {
        val ret = SimpleHelloService().sayHello(name)
        Assertions.assertThat(ret).isEqualTo("Hello $name")
    }
}