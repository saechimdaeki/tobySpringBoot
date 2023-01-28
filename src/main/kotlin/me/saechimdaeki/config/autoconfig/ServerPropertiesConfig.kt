package me.saechimdaeki.config.autoconfig

import me.saechimdaeki.config.MyAutoConfiguration
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

@MyAutoConfiguration
class ServerPropertiesConfig {

//    @Bean
//    fun serverProperties(env: Environment): ServerProperties {
//        return Binder.get(env).bind("",ServerProperties::class.java).get()
//
////        return ServerProperties(
////            contextPath = env.getProperty("contextPath"),
////            port = env.getProperty("port")?.toInt() ?: 9090
////        )
//    }
}