package me.saechimdaeki.config.autoconfig

import com.zaxxer.hikari.HikariDataSource
import me.saechimdaeki.config.ConditionalMyOnClass
import me.saechimdaeki.config.EnableMyConfigurationProperties
import me.saechimdaeki.config.MyAutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import org.springframework.jdbc.support.JdbcTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.sql.Driver
import javax.sql.DataSource

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties::class)
@EnableTransactionManagement
class DataSourceConfig {

    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    @ConditionalOnMissingBean
    fun hikariDataSource(properties: MyDataSourceProperties) : DataSource {
        val dataSource = HikariDataSource()

        dataSource.driverClassName = properties.driverClassName
        dataSource.jdbcUrl = properties.url
        dataSource.username = properties.username
        dataSource.password = properties.password

        return dataSource
    }

    @Bean
    @ConditionalOnMissingBean
    fun dataSource(properties: MyDataSourceProperties): DataSource {
        val dataSource = SimpleDriverDataSource()

        dataSource.setDriverClass(Class.forName(properties.driverClassName) as Class<out Driver>)
        dataSource.url = properties.url
        dataSource.username = properties.username
        dataSource.password = properties.password

        return dataSource
    }


    @Bean
    // 빈 메서드가 실행될떄 스프링 컨테이너에 딱 이 dataSource타입의 빈이 딱 한개만 등록되어 있다면 그것을 가져와서 사용한다
    @ConditionalOnSingleCandidate(DataSource::class)
    @ConditionalOnMissingBean
    fun jdbcTemplate(dataSource: DataSource) : JdbcTemplate {
        return JdbcTemplate(dataSource)
    }


    @Bean
    @ConditionalOnSingleCandidate(DataSource::class)
    @ConditionalOnMissingBean
    fun jdbcTransactionManager(dataSource: DataSource) : JdbcTransactionManager {
        return JdbcTransactionManager(dataSource)
    }

}