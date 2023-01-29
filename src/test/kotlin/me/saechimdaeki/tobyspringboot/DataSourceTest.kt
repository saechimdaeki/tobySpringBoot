package me.saechimdaeki.tobyspringboot

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import javax.sql.DataSource

@HelloBootTest
class DataSourceTest @Autowired constructor(
    val datasource: DataSource
){

    @Test
    fun connect() {
        val connection = datasource.connection
        connection.close()
    }

}