package me.saechimdaeki.tobyspringboot

import me.saechimdaeki.tobyspringboot.simple.HelloController
import me.saechimdaeki.tobyspringboot.simple.HelloService
import me.saechimdaeki.tobyspringboot.simple.SimpleHelloService
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

@Configuration
class HelloBootApplication {

    @Bean
    fun helloController(helloService: HelloService): HelloController {
        return HelloController(helloService)
    }

    @Bean
    fun helloService(): HelloService {
        return SimpleHelloService()
    }
}

fun main(args: Array<String>) {

    val applicationContext = object : AnnotationConfigWebApplicationContext() {
        @Suppress("INAPPLICABLE_JVM_NAME")
        @JvmName(name = "이놈의 classLoadeer ")
        override fun setClassLoader(classLoader: ClassLoader) {
        }

        override fun refresh() {
            super.refresh()
            TomcatServletWebServerFactory().also {

                val webServer = it.getWebServer(ServletContextInitializer { servletContext ->

                    servletContext.addServlet("dispatcherServlet", DispatcherServlet(this))
                        .addMapping("/*")
                })
                webServer.start() // 톰캣이 실행됨
            }
        }
    }.also {
        it.register(HelloBootApplication::class.java)
        it.refresh()
    }


//    val applicationContext = object : GenericWebApplicationContext() {
//        override fun refresh() {
//            super.refresh()
//            TomcatServletWebServerFactory().also {
//
//                val webServer = it.getWebServer(ServletContextInitializer { servletContext ->
//
//                    servletContext.addServlet("dispatcherServlet", DispatcherServlet(this))
//                        .addMapping("/*")
//                })
//                webServer.start() // 톰캣이 실행됨
//            }
//            println("=====")
//            println(classLoader)
//        }
//
//    }.also {
//        it.registerBean(HelloController::class.java, Supplier { HelloController(SimpleHelloService()) })
//        it.registerBean(SimpleHelloService::class.java, Supplier { SimpleHelloService() })
//        it.refresh()
//    }

    /**
     * 빈 서블릿 컨테이너 띄우기 (임베디드 톰캣)
     */

//    TomcatServletWebServerFactory().apply {
//        val webServer = this.getWebServer(ServletContextInitializer { servletContext ->
//
////            val helloController = HelloController() 스프링 컨테이너 사용으로 인한 주석
//            // 서블릿 등록
///*            servletContext.addServlet("frontcontroller", object : HttpServlet() {
//                override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
//                    // 인증, 보안, 다국어, 공통 기능
//                    when {
//                        req.requestURI == "/hello" && req.method == HttpMethod.GET.name -> {
//                            val name = req.getParameter("name") ?: "SAE_CHIM_DAEKI"
//                            val helloController = applicationContext.getBean(HelloController::class.java)
//
//                            val returnValue = helloController.hello(name);
//
//                            resp.status = HttpStatus.OK.value()
//                            resp.contentType = MediaType.TEXT_PLAIN_VALUE
//                            resp.writer.println(returnValue)
//                        }
//                        else -> {
//                            resp.status = HttpStatus.NOT_FOUND.value()
//                        }
//                    }
//                }
//            }).addMapping("/*") // 프론트 컨트롤러로 전환
////                .addMapping("/hello") //hello로 들어오는 요청은 이 서블릿이 처리함.*/
//
// */
//
//            /**
//             * DispatcherServlet으로 전환
//             */
////            servletContext.addServlet("dispatcherServlet", DispatcherServlet(applicationContext))
////                .addMapping("/*")
////        })
////        webServer.start() // 톰캣이 실행됨
//        })
//    }



}



