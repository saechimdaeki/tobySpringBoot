package me.saechimdaeki.tobyspringboot

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [HelloBootApplication::class])
@TestPropertySource("classpath:/application.properties")
@Transactional
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE,AnnotationTarget.CLASS)
annotation class HelloBootTest()
