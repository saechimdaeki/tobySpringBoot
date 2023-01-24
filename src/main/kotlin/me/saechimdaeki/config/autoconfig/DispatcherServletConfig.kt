package me.saechimdaeki.config.autoconfig

import me.saechimdaeki.config.MyAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.DispatcherServlet

@MyAutoConfiguration
class DispatcherServletConfig {

    @Bean
    fun dispatcherServlet(): DispatcherServlet {
        //이 디스패처 서블릿은 팩토리 메소드에서 생성자 없이 오브젝트를 리턴해도 아무 문제없이 동작함
        return DispatcherServlet()
    }
}