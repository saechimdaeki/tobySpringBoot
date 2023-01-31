package me.saechimdaeki.tobyspringboot

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import javax.sql.DataSource

@JdbcTest
class DataSourceTest @Autowired constructor(
    val datasource: DataSource
){

    @Test
    fun connect() {
        val connection = datasource.connection
        connection.close()
    }

}