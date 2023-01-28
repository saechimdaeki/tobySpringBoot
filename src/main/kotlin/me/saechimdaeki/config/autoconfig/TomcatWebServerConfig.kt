package me.saechimdaeki.config.autoconfig

import me.saechimdaeki.config.ConditionalMyOnClass
import me.saechimdaeki.config.MyAutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
//@Conditional(TomcatWebServerConfig.TomcatCondition::class)
class TomcatWebServerConfig(
//    @Value("\${contextPath:}") val ctxPath: String,
//    @Value("\${port:8080}") val port: Int
) {


    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean
    fun servletWebServerFactory(properties: ServerProperties): ServletWebServerFactory {

        return TomcatServletWebServerFactory()
            .apply {
//                this.contextPath = env.getProperty("contextPath")
                this.contextPath = properties.contextPath
                this.port = properties.port
            }
    }



//    class TomcatCondition : Condition {
//        override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
//            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.classLoader)
//        }
//    }
}