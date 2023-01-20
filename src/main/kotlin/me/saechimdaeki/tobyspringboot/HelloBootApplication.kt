package me.saechimdaeki.tobyspringboot

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.http.HttpHeaders
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

            // 서블릿 등록
            servletContext.addServlet("hello", object : HttpServlet() {
                override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
                    val name = req.getParameter("name")?: "SAE_CHIM_DAEKI"

                    resp.status = HttpStatus.OK.value()
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                    resp.writer.println("Hello $name")
                }
            }).addMapping("/hello") //hello로 들어오는 요청은 이 서블릿이 처리함.
        })

        webServer.start() // 톰캣이 실행됨
    }


}


