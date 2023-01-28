package me.saechimdaeki.config

import me.saechimdaeki.config.autoconfig.ServerProperties
import org.springframework.context.annotation.Import
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Import(MyConfigurationPropertiesImportSelector::class)
annotation class EnableMyConfigurationProperties(val value: KClass<ServerProperties>)
