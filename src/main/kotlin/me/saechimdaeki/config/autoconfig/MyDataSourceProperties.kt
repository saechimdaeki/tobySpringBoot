package me.saechimdaeki.config.autoconfig

@MyConfigurationProperties(prefix = "data")
data class MyDataSourceProperties(
    val driverClassName : String?,
    val url : String?,
    val username : String?,
    val password : String?,
) {


}