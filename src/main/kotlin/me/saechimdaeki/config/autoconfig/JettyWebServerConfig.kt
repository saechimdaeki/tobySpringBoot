package me.saechimdaeki.config.autoconfig

import me.saechimdaeki.config.ConditionalMyOnClass
import me.saechimdaeki.config.MyAutoConfiguration
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.util.ClassUtils

@MyAutoConfiguration
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
//@Conditional(JettyWebServerConfig.JettyCondition::class)
class JettyWebServerConfig {

    @Bean("jettyWebServerFactory")
    fun servletWebServerFactory() : ServletWebServerFactory {
        return JettyServletWebServerFactory()
    }


//    class JettyCondition : Condition{
//        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
//            return ClassUtils.isPresent("org.eclipse.jetty.server.Server", context.classLoader)
//        }
//    }
}