package me.saechimdaeki.config.autoconfig

import me.saechimdaeki.config.MyAutoConfiguration
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean

@MyAutoConfiguration
class TomcatWebServerConfig {

    @Bean
    fun servletWebserverFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
    }
}