package me.saechimdaeki.tobyspringboot

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HelloBootApplication

fun main(args: Array<String>) {
    /**
     * 빈 서블릿 컨테이너 띄우기 (임베디드 톰캣)
     */
    TomcatServletWebServerFactory().apply {
        val webServer = this.getWebServer(ServletContextInitializer { servletContext ->

            // 이건 스프링에서 빈으로 등록안한다는 가정하에 이렇게 하는 것
            val helloController = HelloController()
            // 서블릿 등록
            servletContext.addServlet("frontcontroller", object : HttpServlet() {
                override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
                    // 인증, 보안, 다국어, 공통 기능
                    when {
                        req.requestURI == "/hello" && req.method == HttpMethod.GET.name -> {
                            val name = req.getParameter("name") ?: "SAE_CHIM_DAEKI"
                            val returnValue = helloController.hello(name)

                            resp.status = HttpStatus.OK.value()
                            resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                            resp.writer.println(returnValue)
                        }
                        req.requestURI == "/user" -> {
                            //
                        }
                        else -> {
                            resp.status = HttpStatus.NOT_FOUND.value()
                        }
                    }
                }
            }).addMapping("/*") // 프론트 컨트롤러로 전환
//                .addMapping("/hello") //hello로 들어오는 요청은 이 서블릿이 처리함.
        })

        webServer.start() // 톰캣이 실행됨
    }


}


