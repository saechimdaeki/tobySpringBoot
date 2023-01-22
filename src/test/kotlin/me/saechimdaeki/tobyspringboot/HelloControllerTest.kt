package me.saechimdaeki.tobyspringboot

import me.saechimdaeki.tobyspringboot.simple.HelloController
import me.saechimdaeki.tobyspringboot.simple.HelloService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource

class HelloControllerTest {

    @ParameterizedTest(name = "#{index} test value = {0}")
    @ValueSource(strings = ["spring", "saechimdaeki", "hello World", "toby"])
    fun helloController(name: String) {
        val ret = HelloController(object : HelloService {
            override fun sayHello(name: String?): String? {
                return name
            }
        }).hello(name)

        assertThat(ret).isEqualTo(name)
    }

    @ParameterizedTest(name = "#{index} test value = {0}")
    @NullSource
    @ValueSource(strings = [" ", "         "])
    fun failsHelloController(name : String?){
        assertThatThrownBy {
            val ret = HelloController(object : HelloService {
                override fun sayHello(name: String?): String? {
                    return name
                }
            }).hello(name)
        }.isInstanceOf(IllegalArgumentException::class.java)

    }
}