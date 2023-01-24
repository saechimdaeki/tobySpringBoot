package me.saechimdaeki.config.autoconfig

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TomcatWebServerConfig {

    @Bean
    fun servletWebserverFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
    }
}