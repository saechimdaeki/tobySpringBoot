package me.saechimdaeki.config.autoconfig

import org.springframework.stereotype.Component

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Component
annotation class MyConfigurationProperties(val prefix: String)
