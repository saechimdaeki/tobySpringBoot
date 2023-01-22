package me.saechimdaeki.tobyspringboot

import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class MySpringApplication {
    companion object {
        fun run(applicationClass: Class<*>, vararg arg: String) {

            /**
             * ServletWebServerFactory과 DispatcherServlet를 수동으로 빈으로 등록했으므로 다음과 같이 사용
             */
            val applicationContext = object : AnnotationConfigWebApplicationContext() {
                @Suppress("INAPPLICABLE_JVM_NAME")
                @JvmName(name = "이놈의 classLoadeer ")
                override fun setClassLoader(classLoader: ClassLoader) {
                }

                override fun refresh() {
                    super.refresh()

                    val serverFactory = this.getBean(ServletWebServerFactory::class.java)
                    val dispatcherServlet = this.getBean(DispatcherServlet::class.java)

                    val webServer = serverFactory.getWebServer(ServletContextInitializer { servletContext ->

                        servletContext.addServlet("dispatcherServlet", DispatcherServlet(this))
                            .addMapping("/*")
                    })
                    webServer.start() // 톰캣이 실행됨

                }
            }.also {
                it.register(applicationClass)
                it.refresh()
            }
        }
    }
}