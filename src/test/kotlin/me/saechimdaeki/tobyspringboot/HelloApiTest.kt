package me.saechimdaeki.tobyspringboot

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class HelloApiTest {

    @ParameterizedTest(name = "#{index} test value = {0}")
    @ValueSource(strings = ["spring", "saechimdaeki", "hello World", "toby"])
    fun helloApi(name: String) {

        val res = TestRestTemplate().getForEntity("http://localhost:8080/hello?name={NAME}", String::class.java, name)

        // status code 200
        assertThat(res.statusCode).isEqualTo(HttpStatus.OK)
        // header(content-type) text/plain
        assertThat(res.headers.getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE)
        // body Hello $name
        assertThat(res.body).isEqualTo("* Hello $name *")
    }


    @ParameterizedTest(name = "#{index} test value = {0}")
    @NullSource
    @ValueSource(strings = [" ", "         ", "  "])
    fun failshelloApi(name: String?) {

        val res = TestRestTemplate().getForEntity("http://localhost:8080/hello?name={NAME}", String::class.java, name)

        // status code 500
        assertThat(res.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)

    }
}