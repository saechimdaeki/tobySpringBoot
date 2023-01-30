package me.saechimdaeki.tobyspringboot.simple

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class HelloRepositoryJdbc(
    private val jdbcTemplate: JdbcTemplate
) : HelloRepository {
    
    override fun findHello(name: String?): Hello? {
        return try {
            jdbcTemplate.queryForObject("select * from hello where name = '${name}'", RowMapper { rs, _ ->
                Hello(
                    name = rs.getString("name"),
                    count = rs.getInt("count")
                )
            })
        }catch (e: EmptyResultDataAccessException){
            null
        }
    }

    override fun increaseCount(name: String?) {
        when (val hello = findHello(name)) {
            null -> jdbcTemplate.update("insert into hello values(?,?)",name,1)
            else -> jdbcTemplate.update("update hello set count = ? where name = ?",hello.count+1 , name)
        }
    }
}