package me.saechimdaeki.tobyspringboot

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.sql.DataSource

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [HelloBootApplication::class])
@TestPropertySource("classpath:/application.properties")
class DataSourceTest @Autowired constructor(
    val datasource: DataSource
){

    @Test
    fun connect() {
        val connection = datasource.connection
        connection.close()
    }

}