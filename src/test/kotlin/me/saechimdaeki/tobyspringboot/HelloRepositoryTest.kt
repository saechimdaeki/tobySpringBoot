package me.saechimdaeki.tobyspringboot

import me.saechimdaeki.tobyspringboot.simple.HelloRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class HelloRepositoryTest @Autowired constructor(
    val helloRepository: HelloRepository,
    val jdbcTemplate: JdbcTemplate
) {


    @Test
    fun findHelloFailed() {
        assertThat(helloRepository.findHello("saechimdaeki")).isNull()
    }

    @Test
    fun increaseCount() {
        assertThat(helloRepository.countOf("saechimdaeki")).isEqualTo(0)

        helloRepository.increaseCount("saechimdaeki")
        assertThat(helloRepository.countOf("saechimdaeki")).isEqualTo(1)

        helloRepository.increaseCount("saechimdaeki")
        assertThat(helloRepository.countOf("saechimdaeki")).isEqualTo(2)
    }
}