package me.saechimdaeki.tobyspringboot.simple

import me.saechimdaeki.config.EnableMyAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Configuration
@ComponentScan(basePackages = ["me.saechimdaeki.tobyspringboot.simple"])
@EnableMyAutoConfiguration
annotation class MySpringBootApplication()
