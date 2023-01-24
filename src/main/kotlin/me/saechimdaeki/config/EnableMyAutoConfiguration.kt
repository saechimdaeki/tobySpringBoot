package me.saechimdaeki.config

import me.saechimdaeki.config.autoconfig.DispatcherServletConfig
import me.saechimdaeki.config.autoconfig.TomcatWebServerConfig
import org.springframework.context.annotation.Import

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Import(DispatcherServletConfig::class, TomcatWebServerConfig::class)
annotation class EnableMyAutoConfiguration()
