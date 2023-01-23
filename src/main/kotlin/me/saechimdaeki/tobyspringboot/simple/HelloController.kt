package me.saechimdaeki.tobyspringboot.simple

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
    val helloService: HelloService
) : ApplicationContextAware {

    private val log = LoggerFactory.getLogger(this::class.simpleName)

    /*
        토비님이 사용하시는 Httpie를 사용하면 http -v ":8080/hello?name=Spring" 이렇게 요청할 수 있음.
     */
    @GetMapping("/hello")
    fun hello(name: String?): String? {
        require(StringUtils.hasText(name))

        return helloService.sayHello(name)

    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        log.info("applicationContext 실행 {}", applicationContext)
    }
}