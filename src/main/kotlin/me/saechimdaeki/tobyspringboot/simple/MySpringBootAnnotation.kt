package me.saechimdaeki.tobyspringboot.simple

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Configuration
@ComponentScan(basePackages = ["me.saechimdaeki.tobyspringboot.simple"])
annotation class MySpringBootAnnotation()
