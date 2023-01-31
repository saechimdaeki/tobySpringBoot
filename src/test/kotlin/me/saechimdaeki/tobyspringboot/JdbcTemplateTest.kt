package me.saechimdaeki.tobyspringboot

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate

@JdbcTest
class JdbcTemplateTest @Autowired constructor(
    val jdbcTemplate: JdbcTemplate,
){

    @Test
    fun insertAndQuery() {
        jdbcTemplate.update("insert into hello values(?, ?)", "saechimdaeki",3)
        jdbcTemplate.update("insert into hello values(?, ?)", "spring",1)
        val count = jdbcTemplate.queryForObject("select count(*) from hello", Long::class.java)
        Assertions.assertThat(count).isEqualTo(2)
    }

    @Test
    fun insertAndQuery2() {
        jdbcTemplate.update("insert into hello values(?, ?)", "saechimdaeki",3)
        jdbcTemplate.update("insert into hello values(?, ?)", "spring",1)
        val count = jdbcTemplate.queryForObject("select count(*) from hello", Long::class.java)
        Assertions.assertThat(count).isEqualTo(2)
    }
}