package me.saechimdaeki.config

import org.springframework.context.annotation.Import

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
//@Import(DispatcherServletConfig::class, TomcatWebServerConfig::class)
@Import(MyAutoConfigImportSelector::class)
annotation class EnableMyAutoConfiguration()
