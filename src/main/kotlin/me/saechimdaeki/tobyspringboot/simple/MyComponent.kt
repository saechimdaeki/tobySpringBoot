package me.saechimdaeki.tobyspringboot.simple

import org.springframework.stereotype.Component

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Component
annotation class MyComponent()
