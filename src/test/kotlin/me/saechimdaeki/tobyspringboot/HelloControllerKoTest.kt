package me.saechimdaeki.tobyspringboot

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import me.saechimdaeki.tobyspringboot.simple.HelloController
import me.saechimdaeki.tobyspringboot.simple.SimpleHelloService
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest(HelloController::class)
class HelloControllerKoTest(
    @Autowired
    private val mockmvc: MockMvc,
    @MockkBean
    private val simpleHelloService: SimpleHelloService
) : BehaviorSpec({


    Given("name이 주어진 상태에서 ") {
        val name = "Spring"

        every { simpleHelloService.sayHello(name) } returns "Hello $name"

        When("name을 parameter로 담아서 호출하면") {
            val response = mockmvc.perform(
                get("/hello")
                    .param("name", name)
            ).andReturn().response

            Then("Hello $name 이 응답되어야 한다") {
                response.status shouldBe 200
                response.contentLength shouldBe "Hello $name".length
                response.contentAsString shouldBe "Hello Spring"

            }
        }
    }
})

