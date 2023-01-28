package me.saechimdaeki.config.autoconfig

import me.saechimdaeki.config.MyAutoConfiguration
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.core.env.Environment

@MyAutoConfiguration
class PropertyPostProcessorConfig {

    @Bean
    fun propertyPostProcessor(env: Environment) : BeanPostProcessor{
        return object: BeanPostProcessor  {
            override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
                val annotation =
                    AnnotationUtils.findAnnotation(bean.javaClass, MyConfigurationProperties::class.java)

                return annotation?.let {
                    val attrs = AnnotationUtils.getAnnotationAttributes(it)
                    val prefix = attrs["prefix"] as String
                    return Binder.get(env).bindOrCreate(prefix, bean.javaClass)
                }?: bean
            }
        }
    }
}