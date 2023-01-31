package me.saechimdaeki.tobyspringboot

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication(scanBasePackages = ["me.saechimdaeki.tobyspringboot.simple"])
class TobySpringBootApplication {
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @PostConstruct
    fun init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)")
    }
}

fun main(args: Array<String>) {
    runApplication<TobySpringBootApplication>(*args)
}
