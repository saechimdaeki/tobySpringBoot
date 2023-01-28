package me.saechimdaeki.tobyspringboot.simple

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// 특별하게 빈 사이에서 상호 메소드 호출을 통해 의존관계를 주입을 넣을게 아니라면 관례를 따라 false로 변경
@Configuration(proxyBeanMethods = false)
class WebServerConfiguration {
//
//    @Bean
//    fun customWebServerFactory(): ServletWebServerFactory {
//        return TomcatServletWebServerFactory().apply {
//            port = 9090
//        }
//    }
}