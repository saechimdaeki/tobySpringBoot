package me.saechimdaeki.tobyspringboot.simple

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.DispatcherServlet

@Configuration
class Config {

    @Bean
    fun servletWebserverFactory(): ServletWebServerFactory {
        return TomcatServletWebServerFactory()
    }

    @Bean
    fun dispatcherServlet(): DispatcherServlet {
        //이 디스패처 서블릿은 팩토리 메소드에서 생성자 없이 오브젝트를 리턴해도 아무 문제없이 동작함
        return DispatcherServlet()
    }
}